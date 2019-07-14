static Replacement handleNonDaylightSavingsZone(boolean inJodaTimeContext,String daylightSavingsZone,String fixedOffset){
  if (inJodaTimeContext) {
    String newDescription=SUMMARY + "\n\n" + observesDaylightSavingsMessage("DateTimeZone",daylightSavingsZone,fixedOffset);
    return new Replacement(newDescription,ImmutableList.of(daylightSavingsZone,fixedOffset));
  }
 else {
    String newDescription=SUMMARY + "\n\n" + "This TimeZone will not observe daylight savings. " + "If this is intended, use " + fixedOffset + " instead; to observe daylight savings, use " + daylightSavingsZone + ".";
    return new Replacement(newDescription,ImmutableList.of(fixedOffset,daylightSavingsZone));
  }
}
