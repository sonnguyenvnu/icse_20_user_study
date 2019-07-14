/** 
 * Creates new action object from  {@link ActionRuntime} using default constructor.
 */
protected Object createAction(final Class actionClass){
  try {
    return ClassUtil.newInstance(actionClass);
  }
 catch (  Exception ex) {
    throw new MadvocException("Invalid Madvoc action",ex);
  }
}
