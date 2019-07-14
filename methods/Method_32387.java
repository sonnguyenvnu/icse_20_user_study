/** 
 * Creates a date using the calendar date format. Specification reference: 5.2.3.
 * @param bld  the builder
 * @param fields  the fields
 * @param extended  true to use extended format
 * @param strictISO  true to only allow ISO formats
 * @since 1.1
 */
private static boolean dateByWeek(DateTimeFormatterBuilder bld,Collection<DateTimeFieldType> fields,boolean extended,boolean strictISO){
  boolean reducedPrec=false;
  if (fields.remove(DateTimeFieldType.weekyear())) {
    bld.append(Constants.we);
    if (fields.remove(DateTimeFieldType.weekOfWeekyear())) {
      appendSeparator(bld,extended);
      bld.appendLiteral('W');
      bld.appendWeekOfWeekyear(2);
      if (fields.remove(DateTimeFieldType.dayOfWeek())) {
        appendSeparator(bld,extended);
        bld.appendDayOfWeek(1);
      }
 else {
        reducedPrec=true;
      }
    }
 else {
      if (fields.remove(DateTimeFieldType.dayOfWeek())) {
        checkNotStrictISO(fields,strictISO);
        appendSeparator(bld,extended);
        bld.appendLiteral('W');
        bld.appendLiteral('-');
        bld.appendDayOfWeek(1);
      }
 else {
        reducedPrec=true;
      }
    }
  }
 else   if (fields.remove(DateTimeFieldType.weekOfWeekyear())) {
    bld.appendLiteral('-');
    bld.appendLiteral('W');
    bld.appendWeekOfWeekyear(2);
    if (fields.remove(DateTimeFieldType.dayOfWeek())) {
      appendSeparator(bld,extended);
      bld.appendDayOfWeek(1);
    }
 else {
      reducedPrec=true;
    }
  }
 else   if (fields.remove(DateTimeFieldType.dayOfWeek())) {
    bld.appendLiteral('-');
    bld.appendLiteral('W');
    bld.appendLiteral('-');
    bld.appendDayOfWeek(1);
  }
  return reducedPrec;
}
