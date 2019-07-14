/** 
 * Adds a method reference to the constant pool of the class being build. Does nothing if the constant pool already contains a similar item. <i>This method is intended for  {@link Attribute}sub classes, and is normally not needed by class generators or adapters.</i>
 * @param owner the internal name of the method's owner class.
 * @param name the method's name.
 * @param descriptor the method's descriptor.
 * @param isInterface {@literal true} if {@code owner} is an interface.
 * @return the index of a new or already existing method reference item.
 */
public int newMethod(final String owner,final String name,final String descriptor,final boolean isInterface){
  return symbolTable.addConstantMethodref(owner,name,descriptor,isInterface).index;
}
