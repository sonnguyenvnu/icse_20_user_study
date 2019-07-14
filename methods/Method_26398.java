private static String observesDaylightSavingsMessage(String type,String daylightSavingsZone,String fixedOffset){
  return "This " + type + " will observe daylight savings. " + "If this is intended, use " + daylightSavingsZone + " instead; otherwise use " + fixedOffset + ".";
}
