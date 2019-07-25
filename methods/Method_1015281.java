/** 
 * ?????GBK???ISO-8859-1
 * @param tempSql ???????
 */
public static String isocode(String tempSql){
  String returnString=convertNullCode(tempSql);
  try {
    byte[] ascii=returnString.getBytes("GBK");
    returnString=new String(ascii,"ISO-8859-1");
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return returnString;
}
