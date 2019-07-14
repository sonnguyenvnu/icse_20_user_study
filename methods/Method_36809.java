public long optLongParam(String key){
  if (extras.has(key)) {
    return extras.optLong(key);
  }
  if (style != null && style.extras != null) {
    return style.extras.optLong(key);
  }
  return 0;
}
