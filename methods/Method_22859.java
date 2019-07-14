public void init(Base base){
  this.base=base;
  numberFormat=NumberFormat.getInstance();
  numberFormat.setGroupingUsed(false);
  numberFormat.setMinimumIntegerDigits(digits);
  dateFormat=new SimpleDateFormat("yyMMdd");
}
