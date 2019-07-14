static boolean useTimeArithmetic(DurationField field){
  return field != null && field.getUnitMillis() < DateTimeConstants.MILLIS_PER_HOUR * 12;
}
