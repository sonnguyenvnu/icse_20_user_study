public double optDoubleParam(String key){
  if (extras.has(key)) {
    return extras.optDouble(key);
  }
  if (style != null && style.extras != null) {
    return style.extras.optDouble(key);
  }
  return Double.NaN;
}
