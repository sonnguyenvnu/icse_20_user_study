public static Date getDateByFormat(String date,String format){
  SimpleDateFormat sf=new SimpleDateFormat(format);
  Date result=null;
  try {
    result=sf.parse(date);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  return result;
}
