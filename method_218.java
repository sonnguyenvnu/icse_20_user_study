private Class<?> _XXXXX_(AtomicExpression atomic){
  String columnName=null;
  if (atomic.getKeyType().equals(TokenType.ID)) {
    columnName=parseEntityAttribute(atomic.getKey());
  }
 else   if (atomic.getValueType().equals(TokenType.ID)) {
    columnName=parseEntityAttribute(atomic.getValue());
  }
  if (jdbcEntityDefinition.getInternal().getDisplayNameMap().containsKey(columnName)) {
    try {
      return jdbcEntityDefinition.getColumnType(columnName);
    }
 catch (    NoSuchFieldException e) {
      throw new RuntimeException(e);
    }
  }
 else {
    return null;
  }
}