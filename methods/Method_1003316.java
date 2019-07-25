/** 
 * Executes the SQL commands in a script file against a database.
 * @param url the database URL
 * @param user the user name
 * @param password the password
 * @param fileName the script file
 * @param charset the character set or null for UTF-8
 * @param continueOnError if execution should be continued if an erroroccurs
 */
public static void execute(String url,String user,String password,String fileName,Charset charset,boolean continueOnError) throws SQLException {
  new RunScript().process(url,user,password,fileName,charset,continueOnError);
}
