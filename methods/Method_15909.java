private boolean support(RDBColumnMetaData column){
  if (column.getJdbcType() == JDBCType.VARCHAR) {
    return false;
  }
  Class type=column.getJavaType();
  if (type != null && type.isArray()) {
    type=type.getComponentType();
  }
  return ((type != null && type.isEnum() && EnumDict.class.isAssignableFrom(type) && column.getJavaType().isArray()) || (column.getProperty(USE_DICT_MASK_FLAG).isTrue() && column.getOptionConverter() != null));
}
