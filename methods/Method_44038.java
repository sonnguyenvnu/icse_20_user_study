public static Date convertTimestamp(String timestamp){
  try {
    return org.knowm.xchange.utils.DateUtils.fromISO8601DateString(timestamp);
  }
 catch (  com.fasterxml.jackson.databind.exc.InvalidFormatException e) {
    throw new Error("Date parse failure:" + timestamp);
  }
}
