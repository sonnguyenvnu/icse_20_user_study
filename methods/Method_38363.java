protected void onColumn(final DbSqlBuilder sqlBuilder,final String allColumns){
  int len=allColumns.length();
  int lastNdx=0;
  for (int i=0; i < len; i++) {
    char c=allColumns.charAt(i);
    if (c == ',') {
      sqlBuilder.column(allColumns.substring(lastNdx,i));
      lastNdx=i + 1;
      continue;
    }
    if (c == '[') {
      i=allColumns.indexOf(']',i) + 1;
      if (i == 0) {
        i=len;
      }
    }
  }
  sqlBuilder.column(allColumns.substring(lastNdx));
}
