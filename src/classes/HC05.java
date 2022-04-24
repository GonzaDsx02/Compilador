/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import intface.BT_Connections;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

/**
 *
 * @author Gonzalo
 */
public class HC05 {
    boolean scanFinished = false;
    RemoteDevice hc05device = null;
    String hc05Url;

    public Vector<Device> devices = new Vector<>();

    public void scan(BT_Connections bt) throws Exception {
        // schritt 1 fur Rufung die Scan Methode
        LocalDevice localDevice = LocalDevice.getLocalDevice();

        //schritt 2 fur Rufung die Scan Methode
        DiscoveryAgent agent = localDevice.getDiscoveryAgent();
        
        //schritt 3 fur Rufung die Scan Methode
        agent.startInquiry(DiscoveryAgent.GIAC, new DiscoveryListener() {
            @Override
            public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
                try {//hier begint die Suche
                    String name = btDevice.getFriendlyName(false);
                    RemoteDevice dev = btDevice;
                    Device d = new Device(name, dev);
                    devices.add(d);
                    System.out.println(name + " added");

                } catch (IOException ex) {
                    Logger.getLogger(HC05.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void servicesDiscovered(int i, ServiceRecord[] srs) {
                scanFinished = true;
            }

            @Override
            public void serviceSearchCompleted(int i, int i1) {

            }

            @Override
            public void inquiryCompleted(int i) {
                //System.out.println("search is complet");
                bt.Resultados.add("Analisis en proceso");
            }

        });
    }

    public void conect(String s) throws Exception {
        System.out.println(s);
        if ("HC-05".equals(s)) {
            //System.out.println("connecting");
            BT_Connections.Resultados.add("Conectando...");
            for (int i = 0; i < devices.size(); i++) {
                if ("HC-05".equals(devices.elementAt(i).name)) {
                    hc05device = devices.elementAt(i).hc05device;
                }

            }
            UUID uuid = new UUID(0x1101); //scan for btspp://... services (as HC-05 offers it)
            UUID[] searchUuidSet = new UUID[]{uuid};
            int[] attrIDs = new int[]{
                0x0100 // service name
            };
            scanFinished = false;
            LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(attrIDs, searchUuidSet,
                    hc05device, new DiscoveryListener() {
                        @Override
                        public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
                        }

                        @Override
                        public void inquiryCompleted(int discType) {
                        }

                        @Override
                        public void serviceSearchCompleted(int transID, int respCode) {
                            scanFinished = true;
                        }

                        @Override
                        public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
                            for (int i = 0; i < servRecord.length; i++) {
                                hc05Url = servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
                                if (hc05Url != null) {
                                    break; //take the first one
                                }
                            }
                        }
                    });

            while (!scanFinished) {
                Thread.sleep(500);
            }

            //System.out.println("got it");
        } else {
            //System.out.println("cannot conect");
            BT_Connections.Resultados.add("No se puedo establecer la conexión");
        }

    }

    public void action1() throws Exception {
        if (hc05device != null) {
            String[] s = null;
            StreamConnection streamConnection = (StreamConnection) Connector.open(hc05Url);
            OutputStream os = streamConnection.openOutputStream();
            InputStream is = streamConnection.openInputStream();            
            os.write("1".getBytes()); //just send '1' to the device
            os.close();
            is.close();
            streamConnection.close();
            for (int i = 0; i < 10; i++) {
                s[i]="";
            }
        } else {
            System.out.println("connect first");
            
        }
    }

    public void action2() throws Exception {
        if (hc05device != null) {
            StreamConnection streamConnection = (StreamConnection) Connector.open(hc05Url);
            OutputStream os = streamConnection.openOutputStream();
            InputStream is = streamConnection.openInputStream();

            os.write("0".getBytes()); //just send '0' to the device
            os.close();
            is.close();
            streamConnection.close();
        } else {
            System.out.println("connect first");
        }
    }
}
