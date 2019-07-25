/** 
 * Execute a query and append the result to the buffer.
 * @param conn the connection
 * @param s the statement
 * @param i the index
 * @param size the number of statements
 * @param buff the target buffer
 */
void query(Connection conn,String s,int i,int size,StringBuilder buff){
  if (!(s.startsWith("@") && s.endsWith("."))) {
    buff.append(PageParser.escapeHtml(s + ";")).append("<br />");
  }
  boolean forceEdit=s.startsWith("@edit");
  buff.append(getResult(conn,i + 1,s,size == 1,forceEdit)).append("<br />");
}
