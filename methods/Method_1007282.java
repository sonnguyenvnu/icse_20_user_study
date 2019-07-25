/** 
 * Copies and appends the entries in the given exception table at the end of the exception table in the code attribute edited by this object.
 * @param offset    the value added to the code positions includedin the entries.
 */
public void append(ExceptionTable et,int offset){
  ExceptionTable table=codeAttr.getExceptionTable();
  table.add(table.size(),et,offset);
}
