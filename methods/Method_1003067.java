/** 
 * Set the main attributes to null to make sure the object is no longer used.
 */
protected void invalidate(){
  if (id == -1) {
    throw DbException.throwInternalError();
  }
  setModified();
  id=-1;
  database=null;
  trace=null;
  objectName=null;
}
