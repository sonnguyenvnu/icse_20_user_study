/** 
 * Copies single property to the destination. Exceptions are ignored, so copying continues if destination does not have some of the sources properties.
 */
@Override protected boolean visitProperty(String name,final Object value){
  if (isTargetMap) {
    name=LEFT_SQ_BRACKET + name + RIGHT_SQ_BRACKET;
  }
  beanUtil.setProperty(destination,name,value);
  return true;
}
