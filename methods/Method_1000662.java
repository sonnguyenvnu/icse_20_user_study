@Override protected String _val(Object val){
  Long n=Castors.me().castTo(val,Long.class);
  if (null != n) {
    return String.format(fmt,n);
  }
  return null;
}
