/** 
 * ????????????
 * @return
 */
private static String getOutTradeNo(){
  SimpleDateFormat format=new SimpleDateFormat("MMddHHmmss",Locale.getDefault());
  Date date=new Date();
  String key=format.format(date);
  Random r=new Random();
  key=key + r.nextInt();
  key=key.substring(0,15);
  return key;
}
