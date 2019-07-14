private String getValue(List<String> row,int i){
  String value=row.get(i);
  if (value == null) {
    value=nullText;
  }
  return value;
}
