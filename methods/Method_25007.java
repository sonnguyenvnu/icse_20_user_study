/** 
 * Indicate to close the connection after the Response has been sent.
 * @param close {@code true} to hint connection closing, {@code false} to letconnection be closed by client.
 */
public void closeConnection(boolean close){
  if (close)   this.header.put("connection","close");
 else   this.header.remove("connection");
}
