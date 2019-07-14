/** 
 * ???????? / ??????? str yyyyMMddhhMMss ???
 * @return
 */
@SuppressLint("SimpleDateFormat") public static String getCurrentDateTime(String format){
  return simpleDateFormat(format,new Date());
}
