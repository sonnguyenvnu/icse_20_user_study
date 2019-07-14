/** 
 * Sets object parameter in an advanced way. <p> First, it checks if object is <code>null</code> and invokes <code>setNull</code> if so. If object is not <code>null</code>, it tries to resolve  {@link SqlType sql type} (by looking upor using provided class) and use it for setting data. If sql type is not found, default <code>setObject</code> is invoked.
 */
@SuppressWarnings({"unchecked"}) public Q setObject(final int index,final Object value,final Class<? extends SqlType> sqlTypeClass,final int dbSqlType){
  init();
  if (value == null) {
    setNull(index,Types.NULL);
    return _this();
  }
  final SqlType sqlType;
  if (sqlTypeClass != null) {
    sqlType=SqlTypeManager.get().lookupSqlType(sqlTypeClass);
  }
 else {
    sqlType=SqlTypeManager.get().lookup(value.getClass());
  }
  try {
    if ((sqlType != null) && (dbSqlType != SqlType.DB_SQLTYPE_NOT_AVAILABLE)) {
      sqlType.storeValue(preparedStatement,index,value,dbSqlType);
    }
 else {
      DbUtil.setPreparedStatementObject(preparedStatement,index,value,dbSqlType);
    }
  }
 catch (  SQLException sex) {
    throwSetParamError(index,sex);
  }
  return _this();
}
