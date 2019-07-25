@Override public void configure(String format){
  this.format=format;
  format=fixDateForJdk(format);
  this.dateFormat=new SimpleDateFormat(format);
}
