/* The following code was generated by JFlex 1.4.3 on 30/11/21 15:45 */

package intface;
import java_cup.runtime.Symbol;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 30/11/21 15:45 from the specification file
 * <tt>D:/ING EN SISTEMAS/9no Semestre/Lenguajes y automatas II/Compilador1/src/intface/LexerCup.flex</tt>
 */
class LexerCup implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0,  3,  3,  0,  0,  3,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     3, 31, 41,  0,  0, 34, 28,  0, 35, 36, 34, 32,  3, 33, 40,  4, 
     2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  0, 39, 30, 27, 30,  0, 
     0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 25,  1,  1,  1, 
     1,  1, 24, 26,  1,  1,  1,  1,  1,  1,  1,  0,  0,  0,  0,  1, 
     0, 12, 10, 13, 14,  5, 18, 21,  1, 16,  1,  1, 11, 17,  6,  9, 
    20,  1,  8, 15,  7, 19, 22,  1,  1,  1, 23, 37, 29, 38,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\1\14\2\1\5"+
    "\2\1\1\6\4\1\1\7\1\10\1\11\1\12\1\13"+
    "\1\14\1\15\1\0\2\15\1\4\1\16\15\2\1\17"+
    "\5\2\1\6\1\20\1\21\1\0\1\22\1\23\1\0"+
    "\26\2\1\0\14\2\1\24\3\2\1\25\2\2\1\26"+
    "\2\2\1\3\12\2\1\27\1\2\1\30\1\2\1\31"+
    "\1\2\1\32\2\2\1\33\1\2\1\34\3\2\1\35"+
    "\1\36\1\2\1\37\1\2\1\40\3\2\1\41\4\2"+
    "\1\42\1\43\2\2\1\44\3\2\1\45\1\46\1\2"+
    "\1\47\1\50";

  private static int [] zzUnpackAction() {
    int [] result = new int[162];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\52\0\124\0\176\0\250\0\322\0\374\0\u0126"+
    "\0\u0150\0\u017a\0\u01a4\0\u01ce\0\u01f8\0\u0222\0\u024c\0\u0276"+
    "\0\u02a0\0\u02ca\0\u02f4\0\u031e\0\u0348\0\u02f4\0\u02f4\0\u0372"+
    "\0\u039c\0\u03c6\0\u03f0\0\52\0\52\0\52\0\52\0\52"+
    "\0\u041a\0\u0444\0\52\0\u046e\0\52\0\52\0\u0498\0\u04c2"+
    "\0\u04ec\0\u0516\0\u0540\0\u056a\0\u0594\0\u05be\0\u05e8\0\u0612"+
    "\0\u063c\0\u0666\0\u0690\0\u06ba\0\u06e4\0\u070e\0\u0738\0\u0762"+
    "\0\u078c\0\52\0\52\0\52\0\u07b6\0\u041a\0\52\0\u046e"+
    "\0\u07e0\0\u080a\0\u0834\0\u085e\0\u0888\0\u08b2\0\u08dc\0\u0906"+
    "\0\u0930\0\u095a\0\u0984\0\u09ae\0\u09d8\0\u0a02\0\u0a2c\0\u0a56"+
    "\0\u0a80\0\u0aaa\0\u0ad4\0\u0afe\0\u0b28\0\u0b52\0\u0b7c\0\u0ba6"+
    "\0\u0bd0\0\u0bfa\0\u0c24\0\u0c4e\0\u0c78\0\u0ca2\0\u0ccc\0\u0cf6"+
    "\0\u0d20\0\u0d4a\0\u0d74\0\124\0\u0d9e\0\u0dc8\0\u0df2\0\124"+
    "\0\u0e1c\0\u0e46\0\124\0\u0e70\0\u0e9a\0\52\0\u0ec4\0\u0eee"+
    "\0\u0f18\0\u0f42\0\u0f6c\0\u0f96\0\u0fc0\0\u0fea\0\u1014\0\u103e"+
    "\0\124\0\u1068\0\124\0\u1092\0\124\0\u10bc\0\124\0\u10e6"+
    "\0\u1110\0\124\0\u113a\0\124\0\u1164\0\u118e\0\u11b8\0\124"+
    "\0\124\0\u11e2\0\124\0\u120c\0\124\0\u1236\0\u1260\0\u128a"+
    "\0\124\0\u12b4\0\u12de\0\u1308\0\u1332\0\124\0\124\0\u135c"+
    "\0\u1386\0\124\0\u13b0\0\u13da\0\u1404\0\124\0\124\0\u142e"+
    "\0\124\0\124";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[162];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\3"+
    "\1\11\1\3\1\12\1\3\1\13\1\14\1\15\1\16"+
    "\1\3\1\17\1\20\3\3\1\21\3\3\1\22\1\23"+
    "\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33"+
    "\1\34\1\35\1\36\1\37\1\40\1\41\53\0\2\3"+
    "\1\42\1\0\26\3\16\0\1\43\1\0\1\42\1\4"+
    "\1\42\1\0\26\42\16\0\1\43\1\0\2\42\1\5"+
    "\1\0\26\42\16\0\1\44\4\0\1\45\26\0\1\46"+
    "\17\0\2\3\1\42\1\0\1\3\1\47\10\3\1\50"+
    "\13\3\16\0\1\43\1\0\2\3\1\42\1\0\4\3"+
    "\1\51\21\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\1\52\3\3\1\53\21\3\16\0\1\43\1\0\2\3"+
    "\1\42\1\0\4\3\1\54\21\3\16\0\1\43\1\0"+
    "\2\3\1\42\1\0\17\3\1\55\1\3\1\56\4\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\6\3\1\57"+
    "\1\60\3\3\1\61\12\3\16\0\1\43\1\0\2\3"+
    "\1\42\1\0\1\62\25\3\16\0\1\43\1\0\2\3"+
    "\1\42\1\0\2\3\1\63\10\3\1\64\12\3\16\0"+
    "\1\43\1\0\2\3\1\42\1\0\13\3\1\65\12\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\7\3\1\66"+
    "\6\3\1\67\7\3\16\0\1\43\1\0\2\3\1\42"+
    "\1\0\1\70\25\3\16\0\1\43\1\0\2\3\1\42"+
    "\1\0\1\71\25\3\16\0\1\43\33\0\1\72\52\0"+
    "\1\73\52\0\1\73\47\0\1\46\4\0\1\74\44\0"+
    "\1\46\5\0\1\74\43\0\1\46\57\0\1\75\11\0"+
    "\3\76\1\0\26\76\16\0\1\77\1\0\3\42\1\0"+
    "\26\42\16\0\1\43\1\0\3\100\1\0\26\100\16\0"+
    "\1\77\1\0\2\3\1\42\1\0\2\3\1\101\5\3"+
    "\1\102\15\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\17\3\1\103\6\3\16\0\1\43\1\0\2\3\1\42"+
    "\1\0\2\3\1\104\23\3\16\0\1\43\1\0\2\3"+
    "\1\42\1\0\2\3\1\105\5\3\1\106\15\3\16\0"+
    "\1\43\1\0\2\3\1\42\1\0\2\3\1\107\23\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\4\3\1\110"+
    "\21\3\16\0\1\43\1\0\2\3\1\42\1\0\7\3"+
    "\1\111\16\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\7\3\1\112\16\3\16\0\1\43\1\0\2\3\1\42"+
    "\1\0\7\3\1\113\16\3\16\0\1\43\1\0\2\3"+
    "\1\42\1\0\11\3\1\114\1\115\13\3\16\0\1\43"+
    "\1\0\2\3\1\42\1\0\10\3\1\116\15\3\16\0"+
    "\1\43\1\0\2\3\1\42\1\0\2\3\1\117\23\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\7\3\1\120"+
    "\16\3\16\0\1\43\1\0\2\3\1\42\1\0\1\3"+
    "\1\121\24\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\1\122\25\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\6\3\1\123\17\3\16\0\1\43\1\0\2\3\1\42"+
    "\1\0\1\3\1\124\24\3\16\0\1\43\1\0\2\3"+
    "\1\42\1\0\3\3\1\125\22\3\16\0\1\43\1\0"+
    "\2\3\1\42\1\0\1\3\1\126\24\3\16\0\1\43"+
    "\2\0\1\127\50\0\2\3\1\42\1\0\1\130\25\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\1\131\25\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\1\132\25\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\13\3\1\133"+
    "\12\3\16\0\1\43\1\0\2\3\1\42\1\0\3\3"+
    "\1\134\22\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\4\3\1\135\21\3\16\0\1\43\1\0\2\3\1\42"+
    "\1\0\7\3\1\136\16\3\16\0\1\43\1\0\2\3"+
    "\1\42\1\0\6\3\1\137\17\3\16\0\1\43\1\0"+
    "\2\3\1\42\1\0\20\3\1\140\5\3\16\0\1\43"+
    "\1\0\2\3\1\42\1\0\1\3\1\141\24\3\16\0"+
    "\1\43\1\0\2\3\1\42\1\0\12\3\1\142\13\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\1\143\25\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\4\3\1\144"+
    "\21\3\16\0\1\43\1\0\2\3\1\42\1\0\6\3"+
    "\1\145\17\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\1\146\25\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\3\3\1\147\22\3\16\0\1\43\1\0\2\3\1\42"+
    "\1\0\4\3\1\150\21\3\16\0\1\43\1\0\2\3"+
    "\1\42\1\0\1\3\1\151\24\3\16\0\1\43\1\0"+
    "\2\3\1\42\1\0\12\3\1\152\13\3\16\0\1\43"+
    "\1\0\2\3\1\42\1\0\10\3\1\153\15\3\16\0"+
    "\1\43\1\0\2\3\1\42\1\0\11\3\1\154\14\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\12\3\1\155"+
    "\13\3\16\0\1\43\2\0\1\127\41\0\1\156\6\0"+
    "\2\3\1\42\1\0\3\3\1\157\22\3\16\0\1\43"+
    "\1\0\2\3\1\42\1\0\1\3\1\160\24\3\16\0"+
    "\1\43\1\0\2\3\1\42\1\0\3\3\1\161\22\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\15\3\1\162"+
    "\10\3\16\0\1\43\1\0\2\3\1\42\1\0\4\3"+
    "\1\163\21\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\3\3\1\164\22\3\16\0\1\43\1\0\2\3\1\42"+
    "\1\0\3\3\1\165\22\3\16\0\1\43\1\0\2\3"+
    "\1\42\1\0\1\166\25\3\16\0\1\43\1\0\2\3"+
    "\1\42\1\0\7\3\1\167\16\3\16\0\1\43\1\0"+
    "\2\3\1\42\1\0\22\3\1\170\3\3\16\0\1\43"+
    "\1\0\2\3\1\42\1\0\1\171\25\3\16\0\1\43"+
    "\1\0\2\3\1\42\1\0\1\3\1\172\24\3\16\0"+
    "\1\43\1\0\2\3\1\42\1\0\4\3\1\173\21\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\1\3\1\174"+
    "\24\3\16\0\1\43\1\0\2\3\1\42\1\0\2\3"+
    "\1\175\23\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\2\3\1\176\23\3\16\0\1\43\1\0\2\3\1\42"+
    "\1\0\4\3\1\177\21\3\16\0\1\43\1\0\2\3"+
    "\1\42\1\0\7\3\1\200\16\3\16\0\1\43\1\0"+
    "\2\3\1\42\1\0\4\3\1\201\21\3\16\0\1\43"+
    "\1\0\2\3\1\42\1\0\4\3\1\202\21\3\16\0"+
    "\1\43\1\0\2\3\1\42\1\0\11\3\1\203\14\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\7\3\1\204"+
    "\16\3\16\0\1\43\1\0\2\3\1\42\1\0\13\3"+
    "\1\205\12\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\10\3\1\206\15\3\16\0\1\43\1\0\2\3\1\42"+
    "\1\0\3\3\1\207\22\3\16\0\1\43\1\0\2\3"+
    "\1\42\1\0\23\3\1\210\1\211\1\3\16\0\1\43"+
    "\1\0\2\3\1\42\1\0\7\3\1\212\16\3\16\0"+
    "\1\43\1\0\2\3\1\42\1\0\3\3\1\213\22\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\7\3\1\214"+
    "\16\3\16\0\1\43\1\0\2\3\1\42\1\0\7\3"+
    "\1\215\16\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\1\216\25\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\3\3\1\217\22\3\16\0\1\43\1\0\2\3\1\42"+
    "\1\0\11\3\1\220\14\3\16\0\1\43\1\0\2\3"+
    "\1\42\1\0\3\3\1\221\22\3\16\0\1\43\1\0"+
    "\2\3\1\42\1\0\1\222\25\3\16\0\1\43\1\0"+
    "\2\3\1\42\1\0\10\3\1\223\15\3\16\0\1\43"+
    "\1\0\2\3\1\42\1\0\1\224\25\3\16\0\1\43"+
    "\1\0\2\3\1\42\1\0\13\3\1\225\12\3\16\0"+
    "\1\43\1\0\2\3\1\42\1\0\1\3\1\157\24\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\3\3\1\226"+
    "\22\3\16\0\1\43\1\0\2\3\1\42\1\0\3\3"+
    "\1\227\22\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\7\3\1\230\16\3\16\0\1\43\1\0\2\3\1\42"+
    "\1\0\1\231\25\3\16\0\1\43\1\0\2\3\1\42"+
    "\1\0\3\3\1\232\22\3\16\0\1\43\1\0\2\3"+
    "\1\42\1\0\7\3\1\233\16\3\16\0\1\43\1\0"+
    "\2\3\1\42\1\0\11\3\1\234\14\3\16\0\1\43"+
    "\1\0\2\3\1\42\1\0\11\3\1\235\14\3\16\0"+
    "\1\43\1\0\2\3\1\42\1\0\12\3\1\236\13\3"+
    "\16\0\1\43\1\0\2\3\1\42\1\0\3\3\1\152"+
    "\22\3\16\0\1\43\1\0\2\3\1\42\1\0\3\3"+
    "\1\237\22\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\1\240\25\3\16\0\1\43\1\0\2\3\1\42\1\0"+
    "\4\3\1\241\21\3\16\0\1\43\1\0\2\3\1\42"+
    "\1\0\3\3\1\242\22\3\16\0\1\43";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5208];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\31\1\5\11\1\1\1\0\1\11\1\1"+
    "\2\11\23\1\3\11\1\0\1\1\1\11\1\0\26\1"+
    "\1\0\26\1\1\11\64\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[162];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  LexerCup(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  LexerCup(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          zzR = false;
          break;
        case '\r':
          yyline++;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
          }
          break;
        default:
          zzR = false;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 18: 
          { return new Symbol(sym.TextoSC, yychar, yyline, yytext());
          }
        case 41: break;
        case 9: 
          { return new Symbol(sym.Llave_a, yychar, yyline, yytext());
          }
        case 42: break;
        case 32: 
          { return new Symbol(sym.Cadena, yychar, yyline, yytext());
          }
        case 43: break;
        case 24: 
          { return new Symbol(sym.Ciclo, yychar, yyline, yytext());
          }
        case 44: break;
        case 16: 
          { return new Symbol(sym.Op_logico, yychar, yyline, yytext());
          }
        case 45: break;
        case 33: 
          { return new Symbol(sym.Sensor, yychar, yyline, yytext());
          }
        case 46: break;
        case 25: 
          { return new Symbol(sym.Start, yychar, yyline, yytext());
          }
        case 47: break;
        case 15: 
          { return new Symbol(sym.Si, yychar, yyline, yytext());
          }
        case 48: break;
        case 27: 
          { return new Symbol(sym.T_dato, yychar, yyline, yytext());
          }
        case 49: break;
        case 7: 
          { return new Symbol(sym.Parentesis_a, yychar, yyline, yytext());
          }
        case 50: break;
        case 14: 
          { return new Symbol(sym.Op_asignacion, yychar, yyline, yytext());
          }
        case 51: break;
        case 20: 
          { return new Symbol(sym.Caso, yychar, yyline, yytext());
          }
        case 52: break;
        case 39: 
          { return new Symbol(sym.Recorrido, yychar, yyline, yytext());
          }
        case 53: break;
        case 10: 
          { return new Symbol(sym.Llave_c, yychar, yyline, yytext());
          }
        case 54: break;
        case 31: 
          { return new Symbol(sym.Apagar, yychar, yyline, yytext());
          }
        case 55: break;
        case 28: 
          { return new Symbol(sym.Espera, yychar, yyline, yytext());
          }
        case 56: break;
        case 34: 
          { return new Symbol(sym.Avanzar, yychar, yyline, yytext());
          }
        case 57: break;
        case 37: 
          { return new Symbol(sym.Mientras, yychar, yyline, yytext());
          }
        case 58: break;
        case 36: 
          { return new Symbol(sym.Encender, yychar, yyline, yytext());
          }
        case 59: break;
        case 17: 
          { return new Symbol(sym.Op_incremento, yychar, yyline, yytext());
          }
        case 60: break;
        case 8: 
          { return new Symbol(sym.Parentesis_c, yychar, yyline, yytext());
          }
        case 61: break;
        case 30: 
          { return new Symbol(sym.RotarL, yychar, yyline, yytext());
          }
        case 62: break;
        case 13: 
          { return new Symbol(sym.TextoSA, yychar, yyline, yytext());
          }
        case 63: break;
        case 11: 
          { return new Symbol(sym.P_coma, yychar, yyline, yytext());
          }
        case 64: break;
        case 23: 
          { return new Symbol(sym.Clase, yychar, yyline, yytext());
          }
        case 65: break;
        case 38: 
          { return new Symbol(sym.Notificar, yychar, yyline, yytext());
          }
        case 66: break;
        case 29: 
          { return new Symbol(sym.RotarR, yychar, yyline, yytext());
          }
        case 67: break;
        case 40: 
          { return new Symbol(sym.Retroceder, yychar, yyline, yytext());
          }
        case 68: break;
        case 21: 
          { return new Symbol(sym.Si_No, yychar, yyline, yytext());
          }
        case 69: break;
        case 35: 
          { return new Symbol(sym.Detener, yychar, yyline, yytext());
          }
        case 70: break;
        case 1: 
          { return new Symbol(sym.ERROR, yychar, yyline, yytext());
          }
        case 71: break;
        case 12: 
          { return new Symbol(sym.Punto, yychar, yyline, yytext());
          }
        case 72: break;
        case 4: 
          { /*Ignore*/
          }
        case 73: break;
        case 5: 
          { return new Symbol(sym.Igual, yychar, yyline, yytext());
          }
        case 74: break;
        case 2: 
          { return new Symbol(sym.Identificador, yychar, yyline, yytext());
          }
        case 75: break;
        case 3: 
          { return new Symbol(sym.Numero, yychar, yyline, yytext());
          }
        case 76: break;
        case 6: 
          { return new Symbol(sym.Op_relacional, yychar, yyline, yytext());
          }
        case 77: break;
        case 22: 
          { return new Symbol(sym.Func, yychar, yyline, yytext());
          }
        case 78: break;
        case 19: 
          { return new Symbol(sym.Texto, yychar, yyline, yytext());
          }
        case 79: break;
        case 26: 
          { return new Symbol(sym.Op_booleano, yychar, yyline, yytext());
          }
        case 80: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(sym.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
