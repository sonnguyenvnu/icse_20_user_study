/** 
 * Execute the meta data statement.
 * @param db the database
 * @param systemSession the system session
 * @param listener the database event listener
 */
void execute(Database db,Session systemSession,DatabaseEventListener listener){
  try {
    Prepared command=systemSession.prepare(sql);
    command.setPersistedObjectId(id);
    command.update();
  }
 catch (  DbException e) {
    e=e.addSQL(sql);
    SQLException s=e.getSQLException();
    db.getTrace(Trace.DATABASE).error(s,sql);
    if (listener != null) {
      listener.exceptionThrown(s,sql);
    }
 else {
      throw e;
    }
  }
}
