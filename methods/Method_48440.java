public static TemporalUnit parse(String unitName){
  TemporalUnit unit=unitNames.get(unitName.toLowerCase());
  Preconditions.checkNotNull(unit,"Unknown unit time: %s",unitName);
  return unit;
}
