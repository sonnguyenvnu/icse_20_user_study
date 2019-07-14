private String getStringForDefaultValue(ConfigOption<?> c){
  Object o=c.getDefaultValue();
  if (null == o) {
    return "(no default value)";
  }
 else   if (o instanceof Duration) {
    Duration d=(Duration)o;
    return d.toMillis() + " ms";
  }
 else   if (o instanceof String[]) {
    return Joiner.on(",").join((String[])o);
  }
  return o.toString();
}
