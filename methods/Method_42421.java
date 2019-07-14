/** 
 * getStrFormTime: <br/>
 * @param form ????
 * @param date ??
 * @return
 */
public static String getStrFormTime(String form,Date date){
  SimpleDateFormat sdf=new SimpleDateFormat(form);
  return sdf.format(date);
}
