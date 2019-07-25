/** 
 * Copies and inserts the entries in the given exception table at the beginning of the exception table in the code attribute edited by this object.
 * @param offset    the value added to the code positions includedin the entries.
 */
public void insert(ExceptionTable et,int offset){
  codeAttr.getExceptionTable().add(0,et,offset);
}
