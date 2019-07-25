/** 
 * Extracts specified field from the specified date-time value.
 * @param part the date part
 * @param value the date-time value
 * @param mode the database mode
 * @return extracted field
 */
public static Value extract(String part,Value value,Mode mode){
  Value result;
  int field=getDatePart(part);
  if (field != EPOCH) {
    result=ValueInt.get(getIntDatePart(value,field,mode));
  }
 else {
    if (value instanceof ValueInterval) {
      ValueInterval interval=(ValueInterval)value;
      if (interval.getQualifier().isYearMonth()) {
        interval=(ValueInterval)interval.convertTo(Value.INTERVAL_YEAR_TO_MONTH);
        long leading=interval.getLeading();
        long remaining=interval.getRemaining();
        BigInteger bi=BigInteger.valueOf(leading).multiply(BigInteger.valueOf(31557600)).add(BigInteger.valueOf(remaining * 2592000));
        if (interval.isNegative()) {
          bi=bi.negate();
        }
        return ValueDecimal.get(bi);
      }
 else {
        return ValueDecimal.get(new BigDecimal(IntervalUtils.intervalToAbsolute(interval)).divide(BD_NANOS_PER_SECOND));
      }
    }
    long[] a=DateTimeUtils.dateAndTimeFromValue(value);
    long dateValue=a[0];
    long timeNanos=a[1];
    if (value instanceof ValueTime) {
      result=ValueDecimal.get(BigDecimal.valueOf(timeNanos).divide(BD_NANOS_PER_SECOND));
    }
 else     if (value instanceof ValueDate) {
      result=ValueDecimal.get(BigInteger.valueOf(DateTimeUtils.absoluteDayFromDateValue(dateValue)).multiply(BI_SECONDS_PER_DAY));
    }
 else {
      BigDecimal bd=BigDecimal.valueOf(timeNanos).divide(BD_NANOS_PER_SECOND).add(BigDecimal.valueOf(DateTimeUtils.absoluteDayFromDateValue(dateValue)).multiply(BD_SECONDS_PER_DAY));
      if (value instanceof ValueTimestampTimeZone) {
        result=ValueDecimal.get(bd.subtract(BigDecimal.valueOf(((ValueTimestampTimeZone)value).getTimeZoneOffsetMins() * 60)));
      }
 else {
        result=ValueDecimal.get(bd);
      }
    }
  }
  return result;
}
