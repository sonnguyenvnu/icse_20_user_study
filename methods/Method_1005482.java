/** 
 * {@inheritDoc}
 */
@Override public boolean accepts(ClassMap classMap){
  return srcClassIsAccessible(classMap) || destClassIsAccessible(classMap);
}
