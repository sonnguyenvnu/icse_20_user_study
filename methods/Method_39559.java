/** 
 * Returns the prototypes of the attributes used by this class, its fields and its methods.
 * @return the prototypes of the attributes used by this class, its fields and its methods.
 */
private Attribute[] getAttributePrototypes(){
  Attribute.Set attributePrototypes=new Attribute.Set();
  attributePrototypes.addAttributes(firstAttribute);
  FieldWriter fieldWriter=firstField;
  while (fieldWriter != null) {
    fieldWriter.collectAttributePrototypes(attributePrototypes);
    fieldWriter=(FieldWriter)fieldWriter.fv;
  }
  MethodWriter methodWriter=firstMethod;
  while (methodWriter != null) {
    methodWriter.collectAttributePrototypes(attributePrototypes);
    methodWriter=(MethodWriter)methodWriter.mv;
  }
  return attributePrototypes.toArray();
}
