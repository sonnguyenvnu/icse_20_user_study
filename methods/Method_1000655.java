@Override protected String _val(Object val){
  boolean b=false;
  if (null != val) {
    b=Castors.me().castTo(val,Boolean.class);
  }
  return b ? texts[1] : texts[0];
}
