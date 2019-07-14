/** 
 * Creates a date using the calendar date format. Specification reference: 5.2.1.
 * @param bld  the builder
 * @param fields  the fields
 * @param extended  true to use extended format
 * @param strictISO  true to only allow ISO formats
 * @return true if reduced precision
 * @since 1.1
 */
private static boolean dateByMonth(DateTimeFormatterBuilder bld,Collection<DateTimeFieldType> fields,boolean extended,boolean strictISO){
  boolean reducedPrec=false;
  if (fields.remove(DateTimeFieldType.year())) {
    bld.append(Constants.ye);
    if (fields.remove(DateTimeFieldType.monthOfYear())) {
      if (fields.remove(DateTimeFieldType.dayOfMonth())) {
        appendSeparator(bld,extended);
        bld.appendMonthOfYear(2);
        appendSeparator(bld,extended);
        bld.appendDayOfMonth(2);
      }
 else {
        bld.appendLiteral('-');
        bld.appendMonthOfYear(2);
        reducedPrec=true;
      }
    }
 else {
      if (fields.remove(DateTimeFieldType.dayOfMonth())) {
        checkNotStrictISO(fields,strictISO);
        bld.appendLiteral('-');
        bld.appendLiteral('-');
        bld.appendDayOfMonth(2);
      }
 else {
        reducedPrec=true;
      }
    }
  }
 else   if (fields.remove(DateTimeFieldType.monthOfYear())) {
    bld.appendLiteral('-');
    bld.appendLiteral('-');
    bld.appendMonthOfYear(2);
    if (fields.remove(DateTimeFieldType.dayOfMonth())) {
      appendSeparator(bld,extended);
      bld.appendDayOfMonth(2);
    }
 else {
      reducedPrec=true;
    }
  }
 else   if (fields.remove(DateTimeFieldType.dayOfMonth())) {
    bld.appendLiteral('-');
    bld.appendLiteral('-');
    bld.appendLiteral('-');
    bld.appendDayOfMonth(2);
  }
  return reducedPrec;
}
