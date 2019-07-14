/** 
 * Creates new <code>PathMacro</code> instance.
 */
private PathMacros createPathMacroInstance(){
  try {
    return ClassUtil.newInstance(actionsManager.getPathMacroClass());
  }
 catch (  Exception ex) {
    throw new MadvocException(ex);
  }
}
