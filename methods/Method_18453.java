public T getMostSignificantUnit(){
  if (mContent[OutputUnitType.HOST] != null) {
    return get(OutputUnitType.HOST);
  }
 else   if (mContent[OutputUnitType.CONTENT] != null) {
    return get(OutputUnitType.CONTENT);
  }
 else   if (mContent[OutputUnitType.BACKGROUND] != null) {
    return get(OutputUnitType.BACKGROUND);
  }
 else   if (mContent[OutputUnitType.FOREGROUND] != null) {
    return get(OutputUnitType.FOREGROUND);
  }
 else {
    return get(OutputUnitType.BORDER);
  }
}
