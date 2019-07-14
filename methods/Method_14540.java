@Override public Object getField(String name,Properties bindings){
  if (FLAGGED.equals(name)) {
    return flagged;
  }
 else   if (STARRED.equals(name)) {
    return starred;
  }
  return null;
}
