@Override public Object getField(String name,Properties bindings){
  if ("value".equals(name)) {
    return value;
  }
 else   if ("recon".equals(name)) {
    return recon;
  }
  return null;
}
