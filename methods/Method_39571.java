/** 
 * Adds a name and type to the constant pool of the class being build. Does nothing if the constant pool already contains a similar item. <i>This method is intended for  {@link Attribute}sub classes, and is normally not needed by class generators or adapters.</i>
 * @param name a name.
 * @param descriptor a type descriptor.
 * @return the index of a new or already existing name and type item.
 */
public int newNameType(final String name,final String descriptor){
  return symbolTable.addConstantNameAndType(name,descriptor);
}
