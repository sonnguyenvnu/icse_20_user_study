private String createSetPropertyCode(TableMetaData tableMetaData){
  StringBuilder builder=new StringBuilder();
  builder.append("public void setProperty(String property,Object value){\n");
  int index=0;
  for (  ColumnMetaData column : tableMetaData.getColumns()) {
    String propertyName=column.getAlias();
    Class type=column.getJavaType();
    if (index++ > 0) {
      builder.append("\nelse ");
    }
    builder.append("if(property.intern()==\"").append(propertyName).append("\"||property.intern()==\"").append(column.getName()).append("\"){\n").append("this.set").append(StringUtils.toUpperCaseFirstOne(propertyName)).append("((").append(type.getName()).append(")").append("org.hswebframework.web.bean.FastBeanCopier.DEFAULT_CONVERT.convert(value,").append(type.getName()).append(".class,null));").append("\n}");
  }
  builder.append("}");
  return builder.toString();
}
