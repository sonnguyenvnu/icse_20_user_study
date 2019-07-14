private String createGetPropertyCode(TableMetaData tableMetaData){
  StringBuilder builder=new StringBuilder();
  int index=0;
  builder.append("public Object getProperty(String property){\n");
  for (  ColumnMetaData column : tableMetaData.getColumns()) {
    String propertyName=column.getAlias();
    if (index++ > 0) {
      builder.append("\nelse ");
    }
    builder.append("if(property.intern()==\"").append(propertyName).append("\"||property.intern()==\"").append(column.getName()).append("\"){\n").append("return this.get").append(StringUtils.toUpperCaseFirstOne(propertyName)).append("();").append("\n}");
  }
  builder.append("\nreturn null;\n}");
  return builder.toString();
}
