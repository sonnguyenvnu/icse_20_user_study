public Object convert(String[] ss){
  if (null == ss || ss.length == 0)   return null;
  if (Strings.isBlank(ss[0]))   return null;
  if (null != dfmt) {
    Date o=Times.parseq(dfmt,ss[0]);
    return Castors.me().castTo(o,type);
  }
  return Castors.me().cast(ss[0],String.class,type);
}
