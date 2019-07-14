/** 
 * Collects the attributes of this method into the given set of attribute prototypes.
 * @param attributePrototypes a set of attribute prototypes.
 */
final void collectAttributePrototypes(final Attribute.Set attributePrototypes){
  attributePrototypes.addAttributes(firstAttribute);
  attributePrototypes.addAttributes(firstCodeAttribute);
}
