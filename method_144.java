@Override public Object _XXXXX_(final Class<?> parameter,Object value){
  final String targetType=parameter.getName();
  if ("java.sql.Date".equals(targetType)) {
    value=new java.sql.Date(((java.util.Date)value).getTime());
  }
 else   if ("java.sql.Time".equals(targetType)) {
    value=new java.sql.Time(((java.util.Date)value).getTime());
  }
 else   if ("java.sql.Timestamp".equals(targetType)) {
    final Timestamp tsValue=(Timestamp)value;
    final int nanos=tsValue.getNanos();
    value=new java.sql.Timestamp(tsValue.getTime());
    ((Timestamp)value).setNanos(nanos);
  }
  return value;
}