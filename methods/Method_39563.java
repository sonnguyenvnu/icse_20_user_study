/** 
 * Adds a method type reference to the constant pool of the class being build. Does nothing if the constant pool already contains a similar item. <i>This method is intended for  {@link Attribute}sub classes, and is normally not needed by class generators or adapters.</i>
 * @param methodDescriptor method descriptor of the method type.
 * @return the index of a new or already existing method type reference item.
 */
public int newMethodType(final String methodDescriptor){
  return symbolTable.addConstantMethodType(methodDescriptor).index;
}
