/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intface;

import classes.Objetos;
import classes.Errores;
import classes.LineasCodigo;
import classes.GeneradorIno;
import classes.NumeroLinea;
import classes.Tokens;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;

import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

import javax.swing.plaf.basic.BasicMenuBarUI;
import static javax.swing.JOptionPane.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gonzalo
 */
public class Feel extends javax.swing.JFrame {

    /**
     * Creates new form Feel
     */
    //CREACION DE VARIABLES GLOBALES
    NumeroLinea lines;
    JFileChooser seleccionar = new JFileChooser();
    File archivo;
    FileInputStream entrada;
    FileOutputStream salida;
    FileWriter nuevoDoc;

    boolean b = false; //Bandera para validar el guardar como
    static boolean d; //Bandera para verificar si hay una variable ya declarada

    DefaultTableModel dtm; //Se utiliza para manipular la tabla de simbolos (agregar, eliminar, etc).
    TablaSimbolos t = new TablaSimbolos(); //Frame de la tabla de simbolos.
    JTable tblSimbolos = t.getTabla(); //Tabla de simbolos vinculada a la del jframe de la clase TablaSimbolos

    CodigoIntermedio c = new CodigoIntermedio();
    Gramaticas g = new Gramaticas();

    DefaultStyledDocument doc;

    public static ArrayList<Errores> Errores = new ArrayList<>();//Almacena todos los errores que se van recuperando de los analisis sintactico y semantico.
    public static ArrayList<Objetos> Objetos = new ArrayList<Objetos>(); //Cada una de las variables que se van creando
    public static ArrayList<LineasCodigo> Lineas = new ArrayList<LineasCodigo>();
    public static ArrayList<LineasCodigo> LineasCond = new ArrayList<LineasCodigo>();
    public static ArrayList<LineasCodigo> LineasMac = new ArrayList<LineasCodigo>();
    public static ArrayList<LineasCodigo> CodigoObjeto = new ArrayList<LineasCodigo>();
    public static ArrayList<LineasCodigo> CodObjRec = new ArrayList<LineasCodigo>();
    public static ArrayList Producciones = new ArrayList();
    //private String[] metodos_recorrido = {"encender()", "apagar()", "avanzar()", "retroceder()", "rotarR()", "rotarL()", "detener()"};
    static Objetos tempRec;

    public Feel() {
        initComponents();
        setIconImage(getIconImage());
        this.pack();
        menuBar.setUI(new BasicMenuBarUI() {
            public void paint(Graphics g, JComponent c) {
                g.setColor(new Color(19, 62, 88));
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        });
        mainContainer.setBackground(new Color(73, 135, 175));
        numerarLinea();
        //codeArea.setBackground(new Color(5, 25, 39)); //82, 82, 82   5, 25, 39       6, 29, 45
        codeArea.setForeground(new Color(220, 220, 208));
        //this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setTitle("eCuhhmpiler");
        dtm = (DefaultTableModel) tblSimbolos.getModel();
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("recursos/icons8-robot-100.png"));
        return retValue;
    }

    // METODO PARA CREAR LAS ULTIMAS CADENAS
    private int findLastNonWordChar(String text, int index) {
        while (--index >= 0) {
            //  \\W = [A-Za-z0-9]
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    // METODO PARA ENCONTRAR LAS PRIMERAS CADENAS
    private int findFirstNonWordChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }

    /// METODO PARA PINTAR LAS PALABRAS RESERVADAS
    private void colors() {
        int p = codeArea.getCaretPosition();
        final StyleContext cont = StyleContext.getDefaultStyleContext();

        //COLOR
        //final AttributeSet attorange = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(253, 166, 22));
        final AttributeSet attorange = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(255, 159, 37));
        final AttributeSet attwhite = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(252, 252, 252));
        final AttributeSet attblue = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(101, 209, 250));
        final AttributeSet attpurple = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(207, 97, 255));
        final AttributeSet attnumbers = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(115, 255, 149));

        //STYLE
        doc = new DefaultStyledDocument() {
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                        if (text.substring(wordL, wordR).matches("(\\W)*(entero|decimal|cadena|booleano|recorrido)")) {
                            setCharacterAttributes(wordL, wordR - wordL, attpurple, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(encender|apagar|avanzar|retroceder|rotarL|rotarR|detener|espera)")) {
                            setCharacterAttributes(wordL, wordR - wordL, attblue, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(si|sino|ciclo|mientras|caso|Sensor|notificar)")) {
                            setCharacterAttributes(wordL, wordR - wordL, attorange, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(func|clase|start)")) {
                            setCharacterAttributes(wordL, wordR - wordL, attorange, false);
                        } else if (text.substring(wordL, wordR).matches("\\W*[0|1|2|3|4|5|6|7|8|9]+")) {
                            setCharacterAttributes(wordL + 1, wordR - wordL + 1, attnumbers, false);
                        } else {
                            setCharacterAttributes(wordL, wordR - wordL, attwhite, false);
                        }
                        wordL = wordR;
                    }
                    wordR++;
                }
            }

            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) {
                    before = 0;
                }
            }
        };

        JTextPane txt = new JTextPane(doc);

        String temp = codeArea.getText();
        codeArea.setStyledDocument(txt.getStyledDocument());
        codeArea.setText(temp);
        codeArea.setCaretPosition(p);
    }

    private void numerarLinea() {
        lines = new NumeroLinea(codeArea);
        scrollLines.setRowHeaderView(lines);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContainer = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnNuevo = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnGuardarComo = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnCompilar = new javax.swing.JButton();
        btnCargarArduino = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnTblSimbolos = new javax.swing.JButton();
        btnGramaticas = new javax.swing.JButton();
        btnCInt = new javax.swing.JButton();
        btnCodObj = new javax.swing.JButton();
        scrollLines = new javax.swing.JScrollPane();
        codeArea = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSalida = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmNuevo = new javax.swing.JMenuItem();
        jmGuardar = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jmLexico = new javax.swing.JMenuItem();
        jmSintactico = new javax.swing.JMenuItem();
        menuTools = new javax.swing.JMenu();
        jmTblSimbolos = new javax.swing.JMenuItem();
        jmGramaticas = new javax.swing.JMenuItem();
        jmCodObj = new javax.swing.JMenuItem();
        jmConnection = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jmDev = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainContainer.setBackground(new java.awt.Color(102, 102, 102));

        jToolBar1.setBackground(new java.awt.Color(19, 62, 88));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnNuevo.setBackground(new java.awt.Color(19, 62, 88));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-agregar-archivo-30 (1).png"))); // NOI18N
        btnNuevo.setToolTipText("New File");
        btnNuevo.setFocusable(false);
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNuevo);

        btnAbrir.setBackground(new java.awt.Color(19, 62, 88));
        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-carpeta-30.png"))); // NOI18N
        btnAbrir.setToolTipText("Open");
        btnAbrir.setFocusable(false);
        btnAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAbrir);

        btnGuardar.setBackground(new java.awt.Color(19, 62, 88));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-guardar-todo-30.png"))); // NOI18N
        btnGuardar.setToolTipText("Save");
        btnGuardar.setFocusable(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGuardar);

        btnGuardarComo.setBackground(new java.awt.Color(19, 62, 88));
        btnGuardarComo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-guardar-como-30.png"))); // NOI18N
        btnGuardarComo.setToolTipText("Save as");
        btnGuardarComo.setFocusable(false);
        btnGuardarComo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardarComo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarComoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGuardarComo);

        jSeparator1.setToolTipText("");
        jToolBar1.add(jSeparator1);

        btnCompilar.setBackground(new java.awt.Color(19, 62, 88));
        btnCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-código-30.png"))); // NOI18N
        btnCompilar.setToolTipText("Analizar codigo");
        btnCompilar.setFocusable(false);
        btnCompilar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCompilar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCompilar);

        btnCargarArduino.setBackground(new java.awt.Color(19, 62, 88));
        btnCargarArduino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-upload-30.png"))); // NOI18N
        btnCargarArduino.setToolTipText("Verificar y subir");
        btnCargarArduino.setFocusable(false);
        btnCargarArduino.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCargarArduino.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCargarArduino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarArduinoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCargarArduino);
        jToolBar1.add(jSeparator2);

        btnTblSimbolos.setBackground(new java.awt.Color(19, 62, 88));
        btnTblSimbolos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-lista-de-ingredientes-30.png"))); // NOI18N
        btnTblSimbolos.setToolTipText("Tabla de simbolos");
        btnTblSimbolos.setFocusable(false);
        btnTblSimbolos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTblSimbolos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTblSimbolos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTblSimbolosActionPerformed(evt);
            }
        });
        jToolBar1.add(btnTblSimbolos);

        btnGramaticas.setBackground(new java.awt.Color(19, 62, 88));
        btnGramaticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-verificar-30.png"))); // NOI18N
        btnGramaticas.setToolTipText("Gramáticas");
        btnGramaticas.setFocusable(false);
        btnGramaticas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGramaticas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGramaticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGramaticasActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGramaticas);

        btnCInt.setBackground(new java.awt.Color(19, 62, 88));
        btnCInt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-online-coding-30.png"))); // NOI18N
        btnCInt.setToolTipText("Codigo Intermedio");
        btnCInt.setEnabled(false);
        btnCInt.setFocusable(false);
        btnCInt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCInt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCInt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCIntActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCInt);

        btnCodObj.setBackground(new java.awt.Color(19, 62, 88));
        btnCodObj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-binary-code-30.png"))); // NOI18N
        btnCodObj.setToolTipText("Código objeto");
        btnCodObj.setEnabled(false);
        btnCodObj.setFocusable(false);
        btnCodObj.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCodObj.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCodObj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCodObjActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCodObj);

        codeArea.setBackground(new java.awt.Color(43, 43, 43));
        codeArea.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        codeArea.setForeground(new java.awt.Color(204, 204, 204));
        codeArea.setCaretColor(new java.awt.Color(255, 255, 255));
        codeArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codeAreaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codeAreaKeyTyped(evt);
            }
        });
        scrollLines.setViewportView(codeArea);

        txtResultado.setEditable(false);
        txtResultado.setBackground(new java.awt.Color(43, 43, 43));
        txtResultado.setColumns(20);
        txtResultado.setForeground(new java.awt.Color(255, 251, 1));
        txtResultado.setRows(10);
        jScrollPane2.setViewportView(txtResultado);

        jPanel1.setBackground(new java.awt.Color(43, 43, 43));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salida", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 204, 0))); // NOI18N

        txtSalida.setEditable(false);
        txtSalida.setBackground(new java.awt.Color(43, 43, 43));
        txtSalida.setColumns(20);
        txtSalida.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        txtSalida.setRows(5);
        jScrollPane3.setViewportView(txtSalida);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createSequentialGroup()
                .addComponent(scrollLines, javax.swing.GroupLayout.PREFERRED_SIZE, 925, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainContainerLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollLines)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        menuBar.setBackground(new java.awt.Color(102, 102, 102));
        menuBar.setForeground(new java.awt.Color(255, 255, 255));

        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("File");

        jmNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-agregar-archivo-30 (1).png"))); // NOI18N
        jmNuevo.setText("Nuevo");
        jmNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmNuevoActionPerformed(evt);
            }
        });
        jMenu1.add(jmNuevo);

        jmGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-guardar-todo-30.png"))); // NOI18N
        jmGuardar.setText("Guardar");
        jmGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(jmGuardar);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-guardar-como-30.png"))); // NOI18N
        jMenuItem4.setText("Guardar como..");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-shorten-urls-30.png"))); // NOI18N
        jMenuItem7.setText("Elegir Ruta");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        menuBar.add(jMenu1);

        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("Edit");
        menuBar.add(jMenu2);

        jMenu4.setForeground(new java.awt.Color(255, 255, 255));
        jMenu4.setText("Run");
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });

        jmLexico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-código-30.png"))); // NOI18N
        jmLexico.setText("Lexico");
        jmLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmLexicoActionPerformed(evt);
            }
        });
        jMenu4.add(jmLexico);

        jmSintactico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-search-property-30.png"))); // NOI18N
        jmSintactico.setText("Sintactico");
        jmSintactico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmSintacticoActionPerformed(evt);
            }
        });
        jMenu4.add(jmSintactico);

        menuBar.add(jMenu4);

        menuTools.setForeground(new java.awt.Color(255, 255, 255));
        menuTools.setText("Tools");

        jmTblSimbolos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-lista-de-ingredientes-30.png"))); // NOI18N
        jmTblSimbolos.setText("Tabla de simbolos");
        jmTblSimbolos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmTblSimbolosActionPerformed(evt);
            }
        });
        menuTools.add(jmTblSimbolos);

        jmGramaticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-verificar-30.png"))); // NOI18N
        jmGramaticas.setText("Gramaticas encontradas");
        jmGramaticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmGramaticasActionPerformed(evt);
            }
        });
        menuTools.add(jmGramaticas);

        jmCodObj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-binary-code-30.png"))); // NOI18N
        jmCodObj.setText("Código objeto");
        jmCodObj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCodObjActionPerformed(evt);
            }
        });
        menuTools.add(jmCodObj);

        menuBar.add(menuTools);

        jmConnection.setForeground(new java.awt.Color(255, 255, 255));
        jmConnection.setText("Connection");

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-bluetooth-30.png"))); // NOI18N
        jMenuItem5.setText("Bluetooth");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jmConnection.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-big-data-30.png"))); // NOI18N
        jMenuItem6.setText("Load on Arduino");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jmConnection.add(jMenuItem6);

        menuBar.add(jmConnection);

        jMenu3.setForeground(new java.awt.Color(255, 255, 255));
        jMenu3.setText("About");

        jmDev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-software-developer-30.png"))); // NOI18N
        jmDev.setText("Desarrolladores");
        jmDev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmDevActionPerformed(evt);
            }
        });
        jMenu3.add(jmDev);

        menuBar.add(jMenu3);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    static ArrayList<String> genErroresL = new ArrayList();

    public static void notificar(Errores error) {
        Errores.add(error);
    }

    private void clearAll() {
        btnCInt.setEnabled(false);
        btnCodObj.setEnabled(false);
        dtm.setRowCount(0);
        Errores.clear();
        Objetos.clear();
        //Automatas.clear();
        txtSalida.setText("");
        Lineas.clear();
        LineasCond.clear();
        LineasMac.clear();
        CodigoObjeto.clear();
        CodObjRec.clear();
        Producciones.clear();
    }

    private void analizar(int btn) {
        long startTime = System.currentTimeMillis();
        clearAll();
        try {
            if (!codeArea.getText().equals("")) {
                analisisLexico(codeArea.getText());
                analisisSintactico(codeArea.getText(), btn);
            } else {
                showMessageDialog(this, "No se encontro ningun codigo para analizar");
            }
        } catch (IOException ex) {
            Logger.getLogger(Feel.class.getName()).log(Level.SEVERE, null, ex);
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        txtSalida.setText(txtSalida.getText() + "\n>> " + elapsedTime + " milisegundos.");
    }

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        analizar(0);
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void objCodeHexGenerator() {
        String code = "";
        //SE ENCARGA DE RECUPERAR LAS LINEAS DEL CODIGO INTERMEDIO
        for (int i = 0; i < CodigoObjeto.size(); i++) {
            code += "\t" + CodigoObjeto.get(i).getLinea() + "\n";
        }
        code += "  exit(0); //finaliza el ciclo loop()\n"
                + "}\n";
        for (int i = 0; i < CodObjRec.size(); i++) {
            code += CodObjRec.get(i).getLinea() + "\n";
        }
        GeneradorIno gen = new GeneradorIno(code);
        gen.generarArchivo();
    }

    private void uploadArduinoHex() {
        try {
            String command = "cmd /c start cmd.exe /K \"" + System.getProperty("user.dir") + "\\HEX\\debug.bat\"";
            Process process = Runtime.getRuntime().exec(command);
            /*String command = System.getProperty("user.dir") + "\\HEX\\debug.cmd";
            //showMessageDialog(this, command);
            Runtime.getRuntime().exec(command);
            txtSalida.setText(txtSalida.getText()+"\nCodgo HEX generado con exito.");*/
        } catch (IOException ioe) {
            Errores e = new Errores(ioe.toString());
            Errores.add(e);
            imprimirErrores();
        }
    }

    public static boolean crearObjeto(Object id, Object td, Object val, int linea, int columna) {
        d = false;
        for (int i = 0; i < Objetos.size(); i++) {
            if (id.equals(Objetos.get(i).getNombre())) {
                Errores e = new Errores("Error semantico. Linea " + (linea + 1) + "\n\tDeclaracion de variable repetida [ " + id + " ]");
                Errores.add(e);
                d = true;
                return false;
            }
        }
        if (!d) {
            if (td.toString().equals("recorrido")) {
                Objetos o = new Objetos(id, td, linea, columna);
                Objetos.add(o);
                if (val.toString().equals("1")) {
                    setUsingRec(o);
                    return true;
                }
            } else {
                Objetos o = new Objetos(id, td, val, linea, columna);
                Objetos.add(o);
                return true;
            }
        }
        return false;
    }

    static void setUsingRec(Objetos o) {
        Feel.tempRec = o;
    }

    public static void endAsign() {
        Feel.tempRec = null;
    }

    public static void addMetodoRec(Object metodo) {
        classes.Objetos.addMetodo(metodo + "()");
    }

    /*public static void addMetodoRec(Object metodo, int val) {
        intface.Objetos.addMetodo(metodo + "(" + val + ")");
    }*/
    private static int verifDeclaracion(Object id) {
        for (int i = 0; i < Objetos.size(); i++) {
            if (id.equals(Objetos.get(i).getNombre())) {
                return i;
            }
        }
        return -1;
    }

    public static boolean añadirValor(Object id, Object val, int idright) {
        int obj = verifDeclaracion(id);
        if (obj != -1) {
            if (!analizarAutomata(Objetos.get(obj).getTipo(), val)) {
                Errores e = new Errores("Error semantico. Linea " + (idright) + "\n\tIncompatibilidad de tipos [ " + val + " ] no puede ser asignado a [ " + Objetos.get(obj).getTipo() + " ]");
                Errores.add(e);
                return false;
            } else {
                Objetos.get(obj).setValor(val);
                return true;
            }
        } else {
            Errores e = new Errores("Error Semantico. Linea " + idright + "\n\tNo se declaró la variable [ " + id + " ]");
            Errores.add(e);
            return false;
        }
    }

    public static boolean agregarValor(Object id, Object operador, Object val, int idright) {
        int obj = verifDeclaracion(id);
        if (obj != -1) {
            if (!operador.equals("++") && !operador.equals("--")) {
                if (!analizarAutomata(Objetos.get(obj).getTipo(), val)) {
                    Errores e = new Errores("Error semantico. Linea " + (idright) + "\n\tIncompatibilidad de tipos [ " + val + " ] no puede ser asignado a [ " + Objetos.get(obj).getTipo() + " ]");
                    Errores.add(e);
                    return false;
                }
            } else {
                if (Objetos.get(obj).getValor() == null) {
                    Errores e = new Errores("Error semantico. Linea " + (idright) + "\n\tLa variable debe estar inicializada. [ " + id + " ]");
                    Errores.add(e);
                    return false;
                } else {
                    switch (operador.toString()) {
                        case "+=":
                            Objetos.get(obj).setValor(Integer.parseInt(Objetos.get(obj).getValor().toString()) + Integer.parseInt(val.toString()));
                            return true;
                        case "-=":
                            Objetos.get(obj).setValor(Integer.parseInt(Objetos.get(obj).getValor().toString()) - Integer.parseInt(val.toString()));
                            return true;
                        case "*=":
                            Objetos.get(obj).setValor(Integer.parseInt(Objetos.get(obj).getValor().toString()) * Integer.parseInt(val.toString()));
                            return true;
                        case "/=":
                            Objetos.get(obj).setValor(Integer.parseInt(Objetos.get(obj).getValor().toString()) / Integer.parseInt(val.toString()));
                            return true;
                        case "++":
                            Objetos.get(obj).setValor(Integer.parseInt(Objetos.get(obj).getValor().toString()) + 1);
                            return true;
                        case "--":
                            Objetos.get(obj).setValor(Integer.parseInt(Objetos.get(obj).getValor().toString()) - 1);
                            return true;
                        default:
                            Errores e = new Errores("Error sintactico. Linea " + (idright) + "\n\tOperador desconocido [ " + operador + " ]");
                            Errores.add(e);
                            return true;
                    }
                }
            }
        } else {
            Errores e = new Errores("Error Semantico. Linea " + (idright + 1) + "\n\tNo se declaró la variable [ " + id + " ]");
            Errores.add(e);
            return false;
        }
        return false;
    }

    public static boolean verificarTipo(Object tipoDato, Object val, int idright) {
        if (!analizarAutomata(tipoDato, val)) {
            Errores e = new Errores("Error semantico. Linea " + (idright + 1) + "\n\tIncompatibilidad de tipos [ " + val + " ] no puede ser asignado a [ " + tipoDato + " ]");
            Errores.add(e);
            return false;
        }
        return true;
    }

    public static void verifCondicion(Object id, Object operador, Object valor, int linea) {
        int obj = verifDeclaracion(id);
        if (obj != -1) {
            Objetos var = Objetos.get(obj);
            final int q0 = 0, q1e = 1, q2 = 2, q1cb = 4,
                    q13 = 13, q77 = 133;
            int estado = q0;
            do {
                switch (estado) {
                    case q0:
                        if (var.getTipo().equals("entero")) {
                            if (operador.toString().matches("\\W*(==|!=|<|>|<=|>=)")) {
                                estado = q1e;
                            } else {
                                estado = q77;
                            }
                        } else if (var.getTipo().equals("cadena") || var.getTipo().equals("booleano")) {
                            if (operador.toString().matches("\\W*(==|!=)")) {
                                estado = q1cb;
                            } else {
                                estado = q77;
                            }
                        } else {
                            estado = q77;
                        }
                        break;
                    case q1e:
                        if (valor.toString().matches("\\W*[0|1|2|3|4|5|6|7|8|9]+")) {
                            estado = q2;
                        } else if (valor.toString().matches("\\w(\\w|\\d)*") && !valor.toString().matches("\\W*(verdadero|falso)")) {
                            int iTemp = verifDeclaracion(valor);
                            if (iTemp != -1) {
                                Objetos oTemp = Objetos.get(iTemp);
                                if (var.getTipo().equals(oTemp.getTipo())) {
                                    estado = q2;
                                } else {
                                    estado = 99999; //ESTADO PARA LOS ERRORES DE INCOMPATIBILIDAD DE TIPOS DE INDENTIFICADORES
                                }
                            } else {
                                Errores e = new Errores("Error semantico. Linea " + (linea + 1) + "\n\tNo se declaró la variable [ " + valor + " ]");
                                Errores.add(e);
                                return;
                            }
                        } else {
                            estado = q13;
                        }
                        break;
                    case q1cb:
                        if (var.getTipo().equals("booleano")) {
                            if (valor.toString().matches("\\W*(verdadero|falso)")) {
                                estado = q2;
                            } else if (valor.toString().matches("[a-zA-Z_]([a-zA-Z_]|\\d)*")) {
                                int iTemp = verifDeclaracion(valor);
                                if (iTemp != -1) {
                                    Objetos oTemp = Objetos.get(iTemp);
                                    if (var.getTipo().equals(oTemp.getTipo())) {
                                        estado = q2;
                                    } else {
                                        estado = 99999; //ESTADO PARA LOS ERRORES DE INCOMPATIBILIDAD DE TIPOS DE INDENTIFICADORES
                                    }
                                } else {
                                    Errores e = new Errores("Error semantico. Linea " + (linea + 1) + "\n\tNo se declaró la variable [ " + valor + " ]");
                                    Errores.add(e);
                                    return;
                                }
                            } else {
                                estado = q13;
                            }
                        } else if (var.getTipo().equals("cadena")) {
                            if (valor.toString().matches("\"\\s*\\D*\\s*(\\w|\\s|\\d)*\\s*\"")) {
                                estado = q2;
                            } else if (var.getTipo().toString().matches("\\D*\\w(\\w|\\d)*") && verifDeclaracion(valor) != -1) {
                                estado = q2;
                            } else {
                                estado = q13;
                            }
                        }
                        break;
                    case q2:
                        //SE ACEPTA EL AUTOMATA
                        return;
                    case q13:
                        Errores e = new Errores("Error semantico. Linea " + (linea + 1) + "\n\tNo se puede comparar [" + valor + "] con un " + var.getTipo() + ".");
                        Errores.add(e);
                        return;
                    case q77:
                        Errores e2 = new Errores("Error semantico. Linea " + (linea + 1) + "\n\tOperadores incompatibles para realizar la comparación.");
                        Errores.add(e2);
                        return;
                    default:
                        //ESTADO 99999
                        Errores e3 = new Errores("Error semantico. Linea " + (linea + 1) + "\n\tNo se pueden comparar variables de tipos diferentes.");
                        Errores.add(e3);
                        return;
                }
            } while (true);
        } else {
            Errores e = new Errores("Error semantico. Linea " + (linea + 1) + "\n\tNo se declaró la variable [ " + id + " ]");
            Errores.add(e);
        }
    }

    public static void analizarMetodo(Object id, int linea) {
        int pos = verifDeclaracion(id);
        if (pos != -1) {
            if (!Objetos.get(pos).getTipo().equals("recorrido")) {
                Errores e = new Errores("Error semantico. Linea " + (linea + 1) + "\n\tEl metodo [ start() ] solo es valido para los datos de tipo [recorrido]");
                Errores.add(e);
            }
        } else {
            Errores e = new Errores("Error semantico. Linea " + (linea + 1) + "\n\tNo se declaró la variable [ " + id + " ]");
            Errores.add(e);
        }
    }

    public void asignarBloque(int line) {
        /*
        if(linea > apertura(bloque(1))){
            id.bloque = 2;
        }
         */
    }

    private static boolean analizarAutomata(Object tipoDato, Object val) {
        int estado = 1;
        do {
            switch (estado) {
                case 1:
                    switch (tipoDato.toString()) {
                        case "entero":
                            estado = 2;
                            break;
                        case "booleano":
                            estado = 3;
                            break;
                        case "cadena":
                            estado = 4;
                            break;
                        default:
                            estado = 0;
                    }
                    break;
                case 2:
                    if (val.toString().matches("\\W*[0|1|2|3|4|5|6|7|8|9]+")) {
                        estado = 7;
                    } else {
                        estado = 0;
                    }
                    break;
                case 3:
                    if (val.toString().matches("\\W*(verdadero|falso)")) {
                        estado = 7;
                    } else {
                        estado = 0;
                    }
                    break;
                case 4:
                    if (val.toString().matches("\\s*\"\\s*(\\w|\\s|\\d)*\"")) {
                        estado = 7;
                    } else {
                        estado = 0;
                    }
                    break;
                case 7:
                    return true;
                case 0:
                    return false;
            }
        } while (true);
    }

    private void jmNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmNuevoActionPerformed
        codeArea.setText("");
        txtResultado.setText("");
        txtSalida.setText("");
        btnCInt.setEnabled(false);
        Lineas.clear();
        LineasCond.clear();
        Objetos.clear();
        Errores.clear();
        dtm.setRowCount(0);
        b = false;
        LineasMac.clear();
        CodigoObjeto.clear();
        CodObjRec.clear();
        Producciones.clear();
    }//GEN-LAST:event_jmNuevoActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed

    }//GEN-LAST:event_jMenu4ActionPerformed

    private void analisisLexico(String codigo) throws IOException {
        int cont = 1;

        String expr = codigo;
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "LINEA " + cont + "\t\t\tSIMBOLO\n";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtResultado.setText(resultado);
                return;
            }
            switch (token) {
                case Linea:
                    cont++;
                    resultado += "LINEA " + cont + "\n";
                    break;
                case Comillas:
                    resultado += "  <Comillas>\t\t" + lexer.lexeme + "\n";
                    break;
                case Cadena:
                    resultado += "  <Tipo de dato>\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Entero:
                    resultado += "  <Tipo de dato>\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Booleano:
                    resultado += "  <Tipo de dato>\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Si:
                    resultado += "  <Reservada Si>\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Si_No:
                    resultado += "  <Reservada Si No>\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Ciclo_Mientras:
                    resultado += "  <Reservada Mientras>\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Ciclo:
                    resultado += "  <Reservada Ciclo>\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Espera:
                    resultado += "  <Reservada Espera>\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Igual:
                    resultado += "  <Op_Igual>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Operador de asignacion", lexer.lexeme);
                    break;
                case Op_logico:
                    resultado += "  <Op_Logico>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Operador lógico", lexer.lexeme);
                    break;
                case Op_incremento:
                    resultado += "  <Op_Incremento>\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Operador incremento", lexer.lexeme);
                    break;
                case Op_relacional:
                    resultado += "  <Op_Relacional>\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Operador relacional", lexer.lexeme);
                    break;
                case Op_asignacion:
                    resultado += "  <Op_Asignación>\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Operador asignación", lexer.lexeme);
                    break;
                case Op_booleano:
                    resultado += "  <Op_Booleano>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Operador booleano", lexer.lexeme);
                    break;
                case Parentesis_a:
                    resultado += "  <Parentes_a>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Delimitador", lexer.lexeme);
                    break;
                case Parentesis_c:
                    resultado += "  <Parentesis_c>\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Delimitador", lexer.lexeme);
                    break;
                case Llave_a:
                    resultado += "  <Llave_a>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Delimitador", lexer.lexeme);
                    break;
                case Llave_c:
                    resultado += "  <Llave_c>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Delimitador", lexer.lexeme);
                    break;
                case Clase:
                    resultado += "  <Reservada Clase>\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Recorrido:
                    resultado += "  <Recorrido>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Avanzar:
                    resultado += "  <Avanzar>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Retroceder:
                    resultado += "  <Retroceder>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Encender:
                    resultado += "  <Encender>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Apagar:
                    resultado += "  <Apagar>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case RotarL:
                    resultado += "  <RotarL>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case RotarR:
                    resultado += "  <RotarR>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Start:
                    resultado += "  <Start>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Sensor:
                    resultado += "  <Sensor>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case Notificar:
                    resultado += "  <Notificar>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Palabra reservada", lexer.lexeme);
                    break;
                case P_coma:
                    resultado += "  <Punto y coma>\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Delimitador", lexer.lexeme);
                    break;
                case Punto:
                    resultado += "  <Punto>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Simbolo", lexer.lexeme);
                    break;
                case Identificador:
                    resultado += "  <Identificador>\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Identificador", lexer.lexeme);
                    break;
                case Numero:
                    resultado += "  <Numero>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Número", lexer.lexeme);
                    break;
                case Texto:
                    resultado += "  <Texto>\t\t" + lexer.lexeme + "\n";
                    añadirSimbolo("Texto", lexer.lexeme);
                    break;
                case ERROR:
                    resultado += "  <Simbolo no definido>\n";
                    txtSalida.setText(txtSalida.getText() + "\nError lexico. [Linea: " + cont + "] Simbolo no definido \"" + lexer.lexeme + "\"");
                    break;
                default:
                    resultado += "  < " + lexer.lexeme + " >\n";
                    añadirSimbolo(lexer.lexeme, lexer.lexeme);
                    break;
            }
        }
    }

    private void jmLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmLexicoActionPerformed
        try {
            analisisLexico(codeArea.getText());
        } catch (IOException ex) {
            Logger.getLogger(Feel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmLexicoActionPerformed

    private void analisisSintactico(String codigo, int btn) {
        String ST = codigo;
        Sintax s = new Sintax(new LexerCup(new StringReader(ST)));
        try {
            s.parse();
            if (!hayErrores()) {
                txtSalida.setForeground(new Color(175, 255, 163));
                //txtSalida.setText(txtSalida.getText() + "\n->Analizado correctamente\n->0 Errores");
                txtSalida.setText(txtSalida.getText() + ">> Compilacion exitosa");
                btnCInt.setEnabled(true);
                btnCodObj.setEnabled(true);
                objCodeHexGenerator();
                if (btn == 1) {
                    uploadArduinoHex();
                }
            } else {
                btnCInt.setEnabled(false);
                btnCodObj.setEnabled(false);
                txtSalida.setForeground(new Color(255, 251, 1));
                imprimirErrores();
                txtSalida.setText(txtSalida.getText() + "\n->Analizado con errores\n->" + getCountErroresString() + "\n");
            }
        } catch (Exception ex) {
            Symbol sym = s.getS();
            txtSalida.setForeground(new Color(255, 251, 1));
            btnCInt.setEnabled(false);
            if (sym != null) {
                imprimirErrores();
                txtSalida.setText(txtSalida.getText() + "\n->Error Sintactico. [Linea: " + (sym.right + 1) + "], " + sym.value);
            } else {
                Logger.getLogger(Feel.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }

    boolean repeat = false;

    public void añadirSimbolo(String tipo, String nombre) {
        for (int i = 0; i < dtm.getRowCount(); i++) {
            if (nombre.equals(dtm.getValueAt(i, 1))) {
                int c = Integer.parseInt(dtm.getValueAt(i, 2).toString());
                dtm.setValueAt(c + 1, i, 2);
                repeat = true;
                break;
            }
        }
        if (!repeat) {
            Object O[] = new Object[3];
            O[0] = tipo;
            O[1] = nombre;
            O[2] = 1;
            dtm.addRow(O);
        }
        repeat = false;
    }

    /*public void checkDecls() throws CuhhException { //[0] = tipo de dato, [1] = nombre de variable
        int rep = 0;
        for (int i = 0; i < Objetos.size(); i++) {
            for (int j = 0; j < Objetos.size(); j++) {
                if (Objetos.get(j).tipo.equals("clase") || Objetos.get(i).tipo.equals("clase")) {
                    if (Objetos.get(j).Nombre.toString().equals(Objetos.get(i).Nombre.toString())) {
                        rep++;
                        if (rep > 1) {
                            txtSalida.setText("->Error Semantico. Linea " + (Objetos.get(i).linea + 1) + " \n\tNo se puede usar el nombre de la clase como un identificador: " + Objetos.get(j).Nombre);
                            throw new CuhhException("Error en la declaracion de variables");
                        }
                    }
                } else if (Objetos.get(j).Nombre.toString().equals(Objetos.get(i).Nombre.toString())) {
                    rep++;
                    if (rep > 1) {
                        txtSalida.setText("->Error Semantico. Linea " + (Objetos.get(i).linea + 1) + "\n\tVariable repetida: " + Objetos.get(j).Nombre);
                        throw new CuhhException("Error en la declaracion de variables");
                    }
                }
            }
            rep = 0;
        }
        //showMessageDialog(this, "Sin errores semanticos");
    }*/

 /*private void verificarOperadores() throws CuhhException {
        for (int i = 0; i < Automatas.size(); i++) {
            for (int j = 0; j < Objetos.size(); j++) {
                switch (Objetos.get(j).getTipo().toString()) {
                    case "entero":
                        Automatas.get(i).setTipoDato(Objetos.get(j).getTipo());
                        break;
                    case "booleano":
                    case "cadena":
                        if (!Automatas.get(i).getOperador().equals("==") || !Automatas.get(i).getOperador().equals("!=")) {
                            throw new CuhhException("Error semantico. Mal uso de los operadores en la condicion \"" + Automatas.get(i).operador + "\"\n\tLinea " + Automatas.get(i).getLinea());
                        } else {
                            break;
                        }
                    default:
                        throw new CuhhException("Error semantico. Ni Dios sabe que paso aqui");
                }

                if (Objetos.get(j).getTipo().equals("entero")) {
                                
                            //throw new CuhhException("Error semantico. Mal uso de los operadores en la condicion \"" + Automatas.get(i).operador + "\"\n\tLinea " + Automatas.get(i).getLinea());
                            } else {
                                Automatas.get(i).setTipoDato(Objetos.get(j).getTipo());
                                dec = true;
                            }
            }
        }
    }*/

 /*private void verificacionVariables() throws CuhhException {
        for (int i = 0; i < Automatas.size(); i++) {
            String id = Automatas.get(i).getIdentificador().toString();
            boolean dec = false;
            if (id.equals("entero") || id.equals("booleano") || id.equals("recorrido") || id.equals("cadena")) {
                throw new CuhhException("Error semantico. No se puede utilizar una palabra reservada como nombre de variable");
            } else {
                if (Automatas.get(i).tipo.equals("declaracion") || Automatas.get(i).tipo.equals("dec_reco")) {
                    dec = true;
                }
                for (int j = 0; j < Objetos.size(); j++) {
                    if (Automatas.get(i).tipo.equals("operacion") || Automatas.get(i).tipo.equals("sent_bool") || Automatas.get(i).tipo.equals("dec_asign") || Automatas.get(i).tipo.equals("sent_reco")) {
                        if (Automatas.get(i).identificador.equals(Objetos.get(j).Nombre) && Automatas.get(i).getLinea() >= Objetos.get(j).getLinea()) {
                            Automatas.get(i).setTipoDato(Objetos.get(j).getTipo());
                            dec = true;
                        }
                    }
                }
                if (!dec) {
                    throw new CuhhException("\nError semantico\n\tNo se declaro la variable \"" + Automatas.get(i).getIdentificador().toString() + "\"\nLinea " + (Automatas.get(i).getLinea() + 1));
                }

            }
        }
    }*/

    private void jmSintacticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSintacticoActionPerformed
        analisisSintactico(codeArea.getText(), 0);
    }//GEN-LAST:event_jmSintacticoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (b == false) {
            if (seleccionar.showDialog(this, "Guardar") == JFileChooser.APPROVE_OPTION) {
                archivo = seleccionar.getSelectedFile();
                String Documento = codeArea.getText();
                String mensaje = GuardarArchivo(archivo, Documento);
                if (mensaje != null) {
                    showMessageDialog(this, mensaje);
                } else {
                    showMessageDialog(this, "Archivo NO compatible");
                }
            }
        } else {
            String mensaje = GuardarArchivo(archivo, codeArea.getText());
            showMessageDialog(this, mensaje);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarComoActionPerformed
        if (seleccionar.showDialog(this, "Guardar") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            String Documento = codeArea.getText();
            String mensaje = GuardarArchivo(archivo, Documento);
            if (mensaje != null) {
                showMessageDialog(this, mensaje);
            } else {
                showMessageDialog(this, "Archivo NO compatible");
            }
        }
    }//GEN-LAST:event_btnGuardarComoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (seleccionar.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("cuhh")) {
                    String documento = AbrirArchivo(archivo);
                    codeArea.setText(documento);
                    b = true;
                    colors();
                } else {
                    showMessageDialog(null, "Archivo NO compatible");
                }
            }
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        codeArea.setText("");
        txtResultado.setText("");
        txtSalida.setText("");
        btnCInt.setEnabled(false);
        btnCodObj.setEnabled(false);
        Lineas.clear();
        LineasCond.clear();
        Objetos.clear();
        Errores.clear();
        dtm.setRowCount(0);
        b = false;
        LineasMac.clear();
        CodigoObjeto.clear();
        CodObjRec.clear();
        Producciones.clear();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnTblSimbolosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTblSimbolosActionPerformed
        t.setVisible(true);
    }//GEN-LAST:event_btnTblSimbolosActionPerformed

    private void codeAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codeAreaKeyTyped

    }//GEN-LAST:event_codeAreaKeyTyped
    boolean init = false;
    int initP, finP;
    private void codeAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codeAreaKeyPressed
        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_C) {
            showMessageDialog(this, "Control + C");
            codeArea.copy();
        } else if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V) {
            //showMessageDialog(this, "Control + V");
            //codeArea.paste();  
        }

        switch (evt.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                codeArea.setCaretPosition(codeArea.getCaretPosition());
                break;
            case KeyEvent.VK_RIGHT:
                codeArea.setCaretPosition(codeArea.getCaretPosition());
                break;
            case KeyEvent.VK_DOWN:
                codeArea.setCaretPosition(codeArea.getCaretPosition());
                break;
            case KeyEvent.VK_UP:
                codeArea.setCaretPosition(codeArea.getCaretPosition());
                break;
            case KeyEvent.VK_TAB:
                evt.consume();
                String tab = "  ";
                int posicion = codeArea.getCaretPosition();
                Element e = doc.getCharacterElement(posicion);
                try {
                    doc.insertString(posicion, tab, e.getAttributes());
                } catch (BadLocationException ex) {
                    showMessageDialog(this, "Error en la posicion del cursor");
                }
                break;
            default:
                colors();
                break;
        }
    }//GEN-LAST:event_codeAreaKeyPressed

    private void btnCIntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCIntActionPerformed
        String CodigoIntermedio = "";
        for (int i = 0; i < Lineas.size(); i++) {
            CodigoIntermedio += (Lineas.get(i).getLinea()) + "\n";
            //System.out.println(Lineas.get(i).getLinea());
        }
        CodigoIntermedio += ("goto FIN\n");
        for (int i = 0; i < LineasCond.size(); i++) {
            CodigoIntermedio += (LineasCond.get(i).getLinea()) + "\n";
        }
        for (int i = 0; i < LineasMac.size(); i++) {
            CodigoIntermedio += (LineasMac.get(i).getLinea()) + "\n";
        }
        CodigoIntermedio += ("FIN:");
        c.setVisible(true);
        c.jtCodigo.setText(CodigoIntermedio);
    }//GEN-LAST:event_btnCIntActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        BT_Connections bt = new BT_Connections();
        bt.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        analizar(1);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jmTblSimbolosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmTblSimbolosActionPerformed
        t.setVisible(true);
    }//GEN-LAST:event_jmTblSimbolosActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        GeneradorIno archivos = new GeneradorIno();
        String ruta = showInputDialog("Ingresa la nueva ruta de destino");
        if (ruta.equals("")) {
            showMessageDialog(this, "Ruta no modificada");
        } else {
            archivos.writeNewRoute(ruta);
            showMessageDialog(this, "Ruta modificada con exito");
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void btnCargarArduinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarArduinoActionPerformed
        analizar(1);
    }//GEN-LAST:event_btnCargarArduinoActionPerformed

    private void jmGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmGuardarActionPerformed
        if (b == false) {
            if (seleccionar.showDialog(this, "Guardar") == JFileChooser.APPROVE_OPTION) {
                archivo = seleccionar.getSelectedFile();
                String Documento = codeArea.getText();
                String mensaje = GuardarArchivo(archivo, Documento);
                if (mensaje != null) {
                    showMessageDialog(this, mensaje);
                } else {
                    showMessageDialog(this, "Archivo NO compatible");
                }
            }
        } else {
            String mensaje = GuardarArchivo(archivo, codeArea.getText());
            showMessageDialog(this, mensaje);
        }
    }//GEN-LAST:event_jmGuardarActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if (seleccionar.showDialog(this, "Guardar") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            String Documento = codeArea.getText();
            String mensaje = GuardarArchivo(archivo, Documento);
            if (mensaje != null) {
                showMessageDialog(this, mensaje);
            } else {
                showMessageDialog(this, "Archivo NO compatible");
            }
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void llenarGramaticas() {
        String gram = "";
        for (int i = 0; i < Producciones.size(); i++) {
            gram += Producciones.get(i).toString() + "\n";
            //System.out.println(Lineas.get(i).getLinea());
        }
        g.setVisible(true);
        g.jtGramaticas.setText(gram);
    }

    private void jmGramaticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmGramaticasActionPerformed
        llenarGramaticas();
    }//GEN-LAST:event_jmGramaticasActionPerformed

    private void btnGramaticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGramaticasActionPerformed
        llenarGramaticas();
    }//GEN-LAST:event_btnGramaticasActionPerformed

    private void openCodObj(){
        File doc = new File(System.getProperty("user.dir") + "/HEX/ruta.dll");
        String route = "";
        try {
            Scanner obj;
            obj = new Scanner(doc);
            while (obj.hasNextLine()) {
                route += obj.nextLine();
            }            
            String[] cad = route.split(" ");                                                                           
            String rutaObj = cad[1];
            
            File codObj = new File(rutaObj + "/Objeto.ino");        
            String content = "";            
            obj = new Scanner(codObj);
            while (obj.hasNextLine()) {
                content += obj.nextLine()+"\n";
            }            
            CodigoObjeto cObj = new CodigoObjeto();
            cObj.txtCodObj.setText(content);
            cObj.setVisible(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneradorIno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void btnCodObjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCodObjActionPerformed
        openCodObj();
    }//GEN-LAST:event_btnCodObjActionPerformed

    private void jmCodObjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCodObjActionPerformed
        openCodObj();
    }//GEN-LAST:event_jmCodObjActionPerformed

    private void jmDevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmDevActionPerformed
        Desarrolladores dev = new Desarrolladores();
        dev.setVisible(true);
    }//GEN-LAST:event_jmDevActionPerformed

    private boolean hayErrores() {
        if (Errores.size() > 0) {
            return true;
        }
        return false;
    }

    private String getCountErroresString() {
        if (Errores.size() > 1 || Errores.size() < 1) {
            return Errores.size() + " Errores";
        } else {
            return Errores.size() + " Error";
        }
    }

    public void imprimirErrores() {
        Collections.sort(Errores, new Comparator<Errores>() {
            @Override
            public int compare(Errores p1, Errores p2) {
                return (new Integer(p1.getLinea()).compareTo(p2.getLinea()));
            }
        });

        for (int i = 0; i < Errores.size(); i++) {
            txtSalida.setText(txtSalida.getText() + "\n" + Errores.get(i).getDescripcion() + "\n");
        }
    }

    public String GuardarArchivo(File archivo, String documento) {
        String mensaje = null;
        try {
            if (!archivo.exists()) {
                salida = new FileOutputStream(archivo + ".cuhh");
                byte[] bytxt = documento.getBytes();
                salida.write(bytxt);
                mensaje = "Archivo guardado";
                b = true;
            } else {
                salida = new FileOutputStream(archivo);
                byte[] bytxt = documento.getBytes();
                salida.write(bytxt);
                mensaje = "Archivo guardado";
            }
        } catch (Exception e) {
        }
        return mensaje;
    }

    public String AbrirArchivo(File archivo) {
        String documento = "";
        try {
            entrada = new FileInputStream(archivo);
            int ascci;
            while ((ascci = entrada.read()) != -1) {
                char caracter = (char) ascci;
                documento += caracter;
            }
        } catch (Exception e) {
        }
        return documento;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Feel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Feel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Feel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Feel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Feel().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCInt;
    private javax.swing.JButton btnCargarArduino;
    private javax.swing.JButton btnCodObj;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnGramaticas;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarComo;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnTblSimbolos;
    private javax.swing.JTextPane codeArea;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem jmCodObj;
    private javax.swing.JMenu jmConnection;
    private javax.swing.JMenuItem jmDev;
    private javax.swing.JMenuItem jmGramaticas;
    private javax.swing.JMenuItem jmGuardar;
    private javax.swing.JMenuItem jmLexico;
    private javax.swing.JMenuItem jmNuevo;
    private javax.swing.JMenuItem jmSintactico;
    private javax.swing.JMenuItem jmTblSimbolos;
    private javax.swing.JPanel mainContainer;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuTools;
    private javax.swing.JScrollPane scrollLines;
    private javax.swing.JTextArea txtResultado;
    private javax.swing.JTextArea txtSalida;
    // End of variables declaration//GEN-END:variables
}
