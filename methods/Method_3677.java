/** 
 * ??????
 * @param str
 * @return
 */
public static int charType(String str){
  if (str != null && str.length() > 0) {
    if (Predefine.CHINESE_NUMBERS.contains(str))     return CT_CNUM;
    byte[] b;
    try {
      b=str.getBytes("GBK");
    }
 catch (    UnsupportedEncodingException e) {
      b=str.getBytes();
      e.printStackTrace();
    }
    byte b1=b[0];
    byte b2=b.length > 1 ? b[1] : 0;
    int ub1=getUnsigned(b1);
    int ub2=getUnsigned(b2);
    if (ub1 < 128) {
      if (ub1 < 32)       return CT_DELIMITER;
      if (' ' == b1)       return CT_OTHER;
      if ('\n' == b1)       return CT_DELIMITER;
      if ("*\"!,.?()[]{}+=/\\;:|".indexOf((char)b1) != -1)       return CT_DELIMITER;
      if ("0123456789".indexOf((char)b1) != -1)       return CT_NUM;
      return CT_SINGLE;
    }
 else     if (ub1 == 162)     return CT_INDEX;
 else     if (ub1 == 163 && ub2 > 175 && ub2 < 186)     return CT_NUM;
 else     if (ub1 == 163 && (ub2 >= 193 && ub2 <= 218 || ub2 >= 225 && ub2 <= 250))     return CT_LETTER;
 else     if (ub1 == 161 || ub1 == 163)     return CT_DELIMITER;
 else     if (ub1 >= 176 && ub1 <= 247)     return CT_CHINESE;
  }
  return CT_OTHER;
}
