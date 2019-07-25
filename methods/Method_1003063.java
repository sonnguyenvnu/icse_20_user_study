/** 
 * INTERNAL
 */
public static synchronized Driver load(){
  try {
    if (!registered) {
      registered=true;
      DriverManager.registerDriver(INSTANCE);
    }
  }
 catch (  SQLException e) {
    DbException.traceThrowable(e);
  }
  return INSTANCE;
}
