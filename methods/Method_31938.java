public long addWrapField(long instant,int years){
  if (years == 0) {
    return instant;
  }
  int thisYear=iChronology.getYear(instant);
  int wrappedYear=FieldUtils.getWrappedValue(thisYear,years,iChronology.getMinYear(),iChronology.getMaxYear());
  return set(instant,wrappedYear);
}
