public Long getTimeStamp() throws ParseException {
  SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
  Date date=sdf.parse(String.valueOf(this.collectTime));
  return date.getTime();
}
