@Override public List<Character> valueFrom(String valueString) throws IllegalArgumentException {
  String[] values=StringUtils.split(valueString,multiValueDelimiter());
  List<Character> chars=new ArrayList<>(values.length);
  for (int i=0; i < values.length; i++) {
    chars.add(values[i].charAt(0));
  }
  return chars;
}
