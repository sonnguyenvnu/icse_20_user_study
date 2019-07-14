public static boolean isNumberType(RDBColumnMetaData columnMetaData){
  return numberType.contains(columnMetaData.getJavaType()) || Number.class.isAssignableFrom(columnMetaData.getJavaType()) || numberJdbcType.contains(columnMetaData.getJdbcType());
}
