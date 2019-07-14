public static String formatHHMM(Date date){
  SimpleDateFormat sf=new SimpleDateFormat("HHmm");
  return sf.format(date);
}
