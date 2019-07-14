/** 
 * Adds a class reference to the constant pool of the class being build. Does nothing if the constant pool already contains a similar item. <i>This method is intended for  {@link Attribute}sub classes, and is normally not needed by class generators or adapters.</i>
 * @param value the internal name of the class.
 * @return the index of a new or already existing class reference item.
 */
public int newClass(final String value){
  return symbolTable.addConstantClass(value).index;
}
