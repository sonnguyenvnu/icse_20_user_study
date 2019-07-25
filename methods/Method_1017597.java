/** 
 * Parses  {@code CommandComplete (B)} message.Status is in the format of "COMMAND OID ROWS" where both 'OID' and 'ROWS' are optional and COMMAND can have spaces within it, like CREATE TABLE.
 * @param status COMMAND OID ROWS message
 * @throws PSQLException in case the status cannot be parsed
 */
public void parse(String status) throws PSQLException {
  if (!Parser.isDigitAt(status,status.length() - 1)) {
    set(0,0);
    return;
  }
  long oid=0;
  long rows=0;
  try {
    int lastSpace=status.lastIndexOf(' ');
    if (Parser.isDigitAt(status,lastSpace + 1)) {
      rows=Parser.parseLong(status,lastSpace + 1,status.length());
      if (Parser.isDigitAt(status,lastSpace - 1)) {
        int penultimateSpace=status.lastIndexOf(' ',lastSpace - 1);
        if (Parser.isDigitAt(status,penultimateSpace + 1)) {
          oid=Parser.parseLong(status,penultimateSpace + 1,lastSpace);
        }
      }
    }
  }
 catch (  NumberFormatException e) {
    throw new PSQLException(GT.tr("Unable to parse the count in command completion tag: {0}.",status),PSQLState.CONNECTION_FAILURE,e);
  }
  set(oid,rows);
}
