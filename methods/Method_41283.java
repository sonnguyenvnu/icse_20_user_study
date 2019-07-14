/** 
 * <p> Get the number of <code> {@link org.quartz.Calendar}</code> s that are stored in the <code>JobsStore</code>. </p>
 */
public int getNumberOfCalendars() throws JobPersistenceException {
  return (Integer)executeWithoutLock(new TransactionCallback(){
    public Object execute(    Connection conn) throws JobPersistenceException {
      return getNumberOfCalendars(conn);
    }
  }
);
}
