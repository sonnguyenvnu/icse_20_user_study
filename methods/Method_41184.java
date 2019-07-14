/** 
 * Create/Lookup a UserTransaction in the InitialContext via the name set in setUserTxLocation().
 */
public static UserTransaction lookupUserTransaction() throws SchedulerException {
  return new UserTransactionWithContext();
}
