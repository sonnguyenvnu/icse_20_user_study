@Override @Deprecated public Object clone(){
  return new CronExpression(this);
}
