public static Long farmatTime(String string){
  Date date=null;
  try {
    SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    date=Date(sf.parse(string));
  }
 catch (  ParseException e) {
    e.printStackTrace();
  }
  return date.getTime();
}
