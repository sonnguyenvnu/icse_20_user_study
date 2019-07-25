final Object apply(Object value,JDBCType asJDBCType) throws SQLServerException {
  if (null == value)   return value;
switch (asJDBCType) {
case INTEGER:
    return oneValueToAnother((Integer)value);
case SMALLINT:
case TINYINT:
  return (short)oneValueToAnother(((Short)value).intValue());
case BIGINT:
return (long)oneValueToAnother(((Long)value).intValue());
case CHAR:
case VARCHAR:
case LONGVARCHAR:
return Integer.toString(oneValueToAnother(Integer.parseInt((String)value)));
default :
DataTypes.throwConversionError("int",asJDBCType.toString());
return value;
}
}
