public static String createUTCDate(SynchronizedValueFactory<Long> nonce){
  DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
  dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
  return dateFormat.format(new Date(nonce.createValue()));
}
