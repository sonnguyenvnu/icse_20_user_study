/** 
 * Set the JNDI URL at which the Application Server's UserTransaction can be found. If not set, the default value is "java:comp/UserTransaction" - which works for nearly all application servers.
 */
public static void setUserTxLocation(String userTxURL){
  if (userTxURL != null) {
    UserTransactionHelper.userTxURL=userTxURL;
  }
}
