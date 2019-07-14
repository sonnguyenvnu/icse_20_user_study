@Override public List<V> valueFrom(String valueString) throws IllegalArgumentException {
  if (StringUtils.isBlank(valueString)) {
    return Collections.emptyList();
  }
  String[] strValues=valueString.split(Pattern.quote("" + multiValueDelimiter()));
  List<V> values=new ArrayList<>(strValues.length);
  for (  String strValue : strValues) {
    values.add(createFrom(strValue));
  }
  return values;
}
