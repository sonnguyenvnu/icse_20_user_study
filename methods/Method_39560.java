/** 
 * Adds a number or string constant to the constant pool of the class being build. Does nothing if the constant pool already contains a similar item. <i>This method is intended for  {@link Attribute} sub classes, and is normally not needed by class generators or adapters.</i>
 * @param value the value of the constant to be added to the constant pool. This parameter must bean  {@link Integer}, a  {@link Float}, a  {@link Long}, a  {@link Double} or a {@link String}.
 * @return the index of a new or already existing constant item with the given value.
 */
public int newConst(final Object value){
  return symbolTable.addConstant(value).index;
}
