/** 
 * Creates a date using the ordinal date format. Specification reference: 5.2.2.
 * @param bld  the builder
 * @param fields  the fields
 * @param extended  true to use extended format
 * @param strictISO  true to only allow ISO formats
 * @since 1.1
 */
private static boolean dateByOrdinal(DateTimeFormatterBuilder bld,Collection<DateTimeFieldType> fields,boolean extended,boolean strictISO){
  boolean reducedPrec=false;
  if (fields.remove(DateTimeFieldType.year())) {
    bld.append(Constants.ye);
    if (fields.remove(DateTimeFieldType.dayOfYear())) {
      appendSeparator(bld,extended);
      bld.appendDayOfYear(3);
    }
 else {
      reducedPrec=true;
    }
  }
 else   if (fields.remove(DateTimeFieldType.dayOfYear())) {
    bld.appendLiteral('-');
    bld.appendDayOfYear(3);
  }
  return reducedPrec;
}
