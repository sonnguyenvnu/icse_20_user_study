@Override protected String _val(Object val){
  Integer n=Castors.me().castTo(val,Integer.class);
  if (null != n) {
    return String.format(fmt,n);
  }
  return null;
}
