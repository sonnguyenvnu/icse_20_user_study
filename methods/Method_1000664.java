@Override protected String _val(Object val){
  if (null != val) {
    if (val.getClass().isArray()) {
      return Lang.concat(", ",(Object[])val).toString();
    }
    if (val instanceof Collection<?>) {
      return Strings.join(", ",(Collection<?>)val);
    }
  }
  String re=Castors.me().castTo(val,String.class);
  if (null != mapping) {
    return Strings.sNull(mapping.get(re),re);
  }
  if (!Strings.isBlank(this.fmt)) {
    return String.format(fmt,re);
  }
  return re;
}
