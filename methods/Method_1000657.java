@Override protected String _val(Object val){
  Double n=Castors.me().castTo(val,Double.class);
  if (null != n) {
    return String.format(fmt,n);
  }
  return null;
}
