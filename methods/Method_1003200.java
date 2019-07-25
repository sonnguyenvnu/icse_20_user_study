/** 
 * Create and start a new writer thread for the given database. If the thread can't be created, this method returns null.
 * @param database the database
 * @param writeDelay the delay
 * @return the writer thread object or null
 */
public static WriterThread create(Database database,int writeDelay){
  try {
    WriterThread writer=new WriterThread(database,writeDelay);
    writer.thread=new Thread(writer,"H2 Log Writer " + database.getShortName());
    Driver.setThreadContextClassLoader(writer.thread);
    writer.thread.setDaemon(true);
    return writer;
  }
 catch (  AccessControlException e) {
    return null;
  }
}
