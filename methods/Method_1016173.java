/** 
 * Replaces specified element with new one.
 * @param element     element to replace
 * @param replacement element replacement
 */
public void replace(final StructureData element,final StructureData replacement){
  if (first == element) {
    first=replacement;
    splitPane.setLeftComponent(replacement.getComponent());
  }
  if (last == element) {
    last=replacement;
    splitPane.setRightComponent(replacement.getComponent());
  }
}
