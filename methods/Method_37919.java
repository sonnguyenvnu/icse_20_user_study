private static void addField(final List<String> row,final String line,final int startIndex,final int endIndex,final boolean inQuoted){
  String field=line.substring(startIndex,endIndex);
  if (inQuoted) {
    field=StringUtil.replace(field,DOUBLE_QUOTE,"\"");
  }
  row.add(field);
}
