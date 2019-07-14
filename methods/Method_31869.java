public long getDateTimeMillis(int year,int monthOfYear,int dayOfMonth,int millisOfDay) throws IllegalArgumentException {
  Chronology base;
  if ((base=iBase) != null && (iBaseFlags & 6) == 6) {
    return base.getDateTimeMillis(year,monthOfYear,dayOfMonth,millisOfDay);
  }
  return super.getDateTimeMillis(year,monthOfYear,dayOfMonth,millisOfDay);
}
