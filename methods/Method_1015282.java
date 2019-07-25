/** 
 * ?????ISO-8859-1???GBK
 * @param tempSql ???????
 * @return
 */
public static String gbkcode(String tempSql){
  String returnString=convertNullCode(tempSql);
  try {
    byte[] ascii=returnString.getBytes("ISO-8859-1");
    returnString=new String(ascii,"GBK");
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return returnString;
}
