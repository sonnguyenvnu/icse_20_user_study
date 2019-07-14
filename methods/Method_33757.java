public static boolean DateCompare(String data1,String data2){
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
  Date d1=null;
  try {
    d1=sdf.parse(data1);
  }
 catch (  ParseException e) {
    return false;
  }
  Date d2=null;
  try {
    d2=sdf.parse(data2);
  }
 catch (  ParseException e) {
    return true;
  }
  if (((d1.getTime() - d2.getTime()) / (24 * 3600 * 1000)) >= 1) {
    return true;
  }
 else {
    return false;
  }
}
