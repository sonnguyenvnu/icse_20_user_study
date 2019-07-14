@JSONField(serialize=false) public String getEqualString(String key,Object value) throws Exception {
  if (JSON.isBooleanOrNumberOrString(value) == false && value instanceof Subquery == false) {
    throw new IllegalArgumentException(key + ":value ?value?????PUT????? [Boolean, Number, String] ???? ?");
  }
  boolean not=key.endsWith("!");
  if (not) {
    key=key.substring(0,key.length() - 1);
  }
  if (StringUtil.isName(key) == false) {
    throw new IllegalArgumentException(key + ":value ?key??????? ! ?????? ?");
  }
  return getKey(key) + (not ? " != " : " = ") + (value instanceof Subquery ? getSubqueryString((Subquery)value) : getValue(value));
}
