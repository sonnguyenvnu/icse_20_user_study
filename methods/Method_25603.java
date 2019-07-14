/** 
 * Build an Array Type from another Type 
 */
public Type arrayTypeForType(Type baseType){
  return new ArrayType(baseType,getSymtab().arrayClass);
}
