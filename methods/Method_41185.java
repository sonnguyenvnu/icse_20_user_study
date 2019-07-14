/** 
 * Return a UserTransaction that was retrieved via getUserTransaction(). This will make sure that the InitalContext used to lookup/create the  UserTransaction is properly cleaned up.
 */
public static void returnUserTransaction(UserTransaction userTransaction){
  if ((userTransaction != null) && (userTransaction instanceof UserTransactionWithContext)) {
    UserTransactionWithContext userTransactionWithContext=(UserTransactionWithContext)userTransaction;
    userTransactionWithContext.closeContext();
  }
}
