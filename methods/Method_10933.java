/** 
 * ? yyyy?MM?dd? ??? ???
 * @param format
 * @param datess
 * @return
 */
public static String string2Timestamp(String format,String datess){
  Date date=string2Date(format,datess);
  return Date2Timestamp(date);
}
