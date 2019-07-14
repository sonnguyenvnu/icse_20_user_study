@Override public long getDateTimeMillis(int year,int monthOfYear,int dayOfMonth,int millisOfDay) throws IllegalArgumentException {
  Chronology base;
  if ((base=getBase()) != null) {
    return base.getDateTimeMillis(year,monthOfYear,dayOfMonth,millisOfDay);
  }
  FieldUtils.verifyValueBounds(DateTimeFieldType.millisOfDay(),millisOfDay,0,DateTimeConstants.MILLIS_PER_DAY - 1);
  return getDateTimeMillis0(year,monthOfYear,dayOfMonth,millisOfDay);
}
