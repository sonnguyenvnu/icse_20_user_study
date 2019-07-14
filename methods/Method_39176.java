@Override protected Redirect resultOf(Object value){
  if (value == null) {
    value=StringPool.EMPTY;
  }
  return Redirect.to((String)value);
}
