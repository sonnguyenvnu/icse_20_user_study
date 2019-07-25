@Override protected String _val(Object val){
  Float n=Castors.me().castTo(val,Float.class);
  if (null != n) {
    return String.format(fmt,n);
  }
  return null;
}
