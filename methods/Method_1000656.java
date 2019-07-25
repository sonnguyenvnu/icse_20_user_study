@Override protected String _val(Object val){
  Date d=Castors.me().castTo(val,Date.class);
  if (null != d)   return Times.format(fmt,d);
  return null == val ? null : val.toString();
}
