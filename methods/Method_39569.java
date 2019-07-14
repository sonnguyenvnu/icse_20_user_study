/** 
 * Adds a field reference to the constant pool of the class being build. Does nothing if the constant pool already contains a similar item. <i>This method is intended for  {@link Attribute}sub classes, and is normally not needed by class generators or adapters.</i>
 * @param owner the internal name of the field's owner class.
 * @param name the field's name.
 * @param descriptor the field's descriptor.
 * @return the index of a new or already existing field reference item.
 */
public int newField(final String owner,final String name,final String descriptor){
  return symbolTable.addConstantFieldref(owner,name,descriptor).index;
}
