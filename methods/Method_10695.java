/** 
 * ????InputStream
 * @param str
 * @return
 */
public static InputStream StringToInputStream(String str){
  InputStream in_nocode=new ByteArrayInputStream(str.getBytes());
  return in_nocode;
}
