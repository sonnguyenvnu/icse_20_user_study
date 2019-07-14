/** 
 * Converter JTX transaction mode to DB transaction mode.
 */
public static DbTransactionMode convertToDbMode(final JtxTransactionMode txMode){
  final int isolation;
switch (txMode.getIsolationLevel()) {
case ISOLATION_DEFAULT:
    isolation=DbTransactionMode.ISOLATION_DEFAULT;
  break;
case ISOLATION_NONE:
isolation=DbTransactionMode.ISOLATION_NONE;
break;
case ISOLATION_READ_COMMITTED:
isolation=DbTransactionMode.ISOLATION_READ_COMMITTED;
break;
case ISOLATION_READ_UNCOMMITTED:
isolation=DbTransactionMode.ISOLATION_READ_UNCOMMITTED;
break;
case ISOLATION_REPEATABLE_READ:
isolation=DbTransactionMode.ISOLATION_REPEATABLE_READ;
break;
case ISOLATION_SERIALIZABLE:
isolation=DbTransactionMode.ISOLATION_SERIALIZABLE;
break;
default :
throw new IllegalArgumentException();
}
return new DbTransactionMode(isolation,txMode.isReadOnly());
}
