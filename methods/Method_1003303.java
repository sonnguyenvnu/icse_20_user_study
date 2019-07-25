/** 
 * Re-compile the view query and all views that depend on this object.
 * @param session the session
 * @param force if exceptions should be ignored
 * @param clearIndexCache if we need to clear view index cache
 * @return the exception if re-compiling this or any dependent view failed(only when force is disabled)
 */
public synchronized DbException recompile(Session session,boolean force,boolean clearIndexCache){
  try {
    compileViewQuery(session,querySQL,false,getName());
  }
 catch (  DbException e) {
    if (!force) {
      return e;
    }
  }
  ArrayList<TableView> dependentViews=new ArrayList<>(getDependentViews());
  initColumnsAndTables(session,false);
  for (  TableView v : dependentViews) {
    DbException e=v.recompile(session,force,false);
    if (e != null && !force) {
      return e;
    }
  }
  if (clearIndexCache) {
    clearIndexCaches(database);
  }
  return force ? null : createException;
}
