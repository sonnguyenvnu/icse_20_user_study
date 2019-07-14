/** 
 * Cleanup helper method that closes the given <code>Statement</code> while ignoring any errors.
 */
public static void closeStatement(Statement statement){
  if (null != statement) {
    try {
      statement.close();
    }
 catch (    SQLException ignore) {
    }
  }
}
