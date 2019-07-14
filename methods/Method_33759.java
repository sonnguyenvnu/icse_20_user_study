public static String timeFormatStr(String time){
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
  Date date=null;
  try {
    date=sdf.parse(time);
  }
 catch (  Exception e) {
    DebugUtil.debug("--????-->","??");
    return time;
  }
  SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  return sdf1.format(date);
}
