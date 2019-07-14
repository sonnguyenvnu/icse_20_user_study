public int halfdayTextToValue(String text){
  String[] halfday=iHalfday;
  for (int i=halfday.length; --i >= 0; ) {
    if (halfday[i].equalsIgnoreCase(text)) {
      return i;
    }
  }
  throw new IllegalFieldValueException(DateTimeFieldType.halfdayOfDay(),text);
}
