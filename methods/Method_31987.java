public int eraTextToValue(String text){
  Integer era=iParseEras.get(text);
  if (era != null) {
    return era.intValue();
  }
  throw new IllegalFieldValueException(DateTimeFieldType.era(),text);
}
