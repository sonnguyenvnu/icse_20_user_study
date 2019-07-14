/** 
 * Sets prepared statement object using target SQL type. Here Jodd makes conversion and not JDBC driver. See: http://www.tutorialspoint.com/jdbc/jdbc-data-types.htm
 */
public static void setPreparedStatementObject(final PreparedStatement preparedStatement,final int index,final Object value,final int targetSqlType) throws SQLException {
  if (value == null) {
    preparedStatement.setNull(index,Types.NULL);
    return;
  }
switch (targetSqlType) {
case Types.VARCHAR:
case Types.LONGVARCHAR:
case Types.CHAR:
    preparedStatement.setString(index,Converter.get().toString(value));
  break;
case Types.INTEGER:
case Types.SMALLINT:
case Types.TINYINT:
preparedStatement.setInt(index,Converter.get().toIntValue(value));
break;
case Types.BIGINT:
preparedStatement.setLong(index,Converter.get().toLongValue(value));
break;
case Types.BOOLEAN:
case Types.BIT:
preparedStatement.setBoolean(index,Converter.get().toBooleanValue(value));
break;
case Types.DATE:
preparedStatement.setDate(index,TypeConverterManager.get().convertType(value,java.sql.Date.class));
break;
case Types.NUMERIC:
case Types.DECIMAL:
preparedStatement.setBigDecimal(index,Converter.get().toBigDecimal(value));
break;
case Types.DOUBLE:
preparedStatement.setDouble(index,Converter.get().toDoubleValue(value));
break;
case Types.REAL:
case Types.FLOAT:
preparedStatement.setFloat(index,Converter.get().toFloatValue(value));
break;
case Types.TIME:
preparedStatement.setTime(index,TypeConverterManager.get().convertType(value,java.sql.Time.class));
break;
case Types.TIMESTAMP:
preparedStatement.setTimestamp(index,TypeConverterManager.get().convertType(value,Timestamp.class));
break;
case Types.BINARY:
case Types.VARBINARY:
preparedStatement.setBytes(index,TypeConverterManager.get().convertType(value,byte[].class));
break;
default :
if (targetSqlType != SqlType.DB_SQLTYPE_NOT_AVAILABLE) {
preparedStatement.setObject(index,value,targetSqlType);
}
 else {
preparedStatement.setObject(index,value);
}
}
}
