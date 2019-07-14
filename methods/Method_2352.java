public static String nowDate(){
  return new Timestamp(instance().currentTimeMillis()).toString();
}
