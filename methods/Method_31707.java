/** 
 * Transforms the details of this SQLException into a nice readable message.
 * @param e The exception.
 * @return The message.
 */
public static String toMessage(SQLException e){
  SQLException cause=e;
  while (cause.getNextException() != null) {
    cause=cause.getNextException();
  }
  String message="SQL State  : " + cause.getSQLState() + "\n" + "Error Code : " + cause.getErrorCode() + "\n";
  if (cause.getMessage() != null) {
    message+="Message    : " + cause.getMessage().trim() + "\n";
  }
  return message;
}
