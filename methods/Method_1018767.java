final Object apply(Object value,JDBCType asJDBCType) throws SQLServerException {
  if (null == value)   return value;
switch (asJDBCType) {
case INTEGER:
case SMALLINT:
    assert (value instanceof Number);
  return zeroOneToYesNo(((Number)value).intValue());
case CHAR:
case VARCHAR:
case LONGVARCHAR:
assert (value instanceof String);
return zeroOneToYesNo(Integer.parseInt((String)value));
default :
DataTypes.throwConversionError("char",asJDBCType.toString());
return value;
}
}
