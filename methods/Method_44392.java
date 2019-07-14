public static Date toDate(String date) throws com.fasterxml.jackson.databind.exc.InvalidFormatException {
  return org.knowm.xchange.utils.DateUtils.fromISO8601DateString(date);
}
