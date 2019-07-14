long getDateMidnightMillis(int year,int monthOfYear,int dayOfMonth) throws IllegalArgumentException {
  return super.getDateMidnightMillis(adjustYearForSet(year),monthOfYear,dayOfMonth);
}
