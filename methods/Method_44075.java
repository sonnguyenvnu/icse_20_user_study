public static Date nanos2Date(String nanos){
  try {
    long millis=Long.valueOf(nanos.substring(0,nanos.length() - 6));
    return new Date(millis);
  }
 catch (  Throwable e) {
    throw new ExchangeException("Invalid timestamp provided by dragonex: " + nanos);
  }
}
