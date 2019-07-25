@Override public Object check(Object val) throws NutValidateException {
  if (null == val)   return null;
  Date d=Castors.me().castTo(val,Date.class);
  if (!range.match(d)) {
    throw new NutValidateException("DateOutOfRange",range.toString(),d);
  }
  return val;
}
