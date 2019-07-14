/** 
 * Adds a dynamic constant reference to the constant pool of the class being build. Does nothing if the constant pool already contains a similar item. <i>This method is intended for  {@link Attribute} sub classes, and is normally not needed by class generators or adapters.</i>
 * @param name name of the invoked method.
 * @param descriptor field descriptor of the constant type.
 * @param bootstrapMethodHandle the bootstrap method.
 * @param bootstrapMethodArguments the bootstrap method constant arguments.
 * @return the index of a new or already existing dynamic constant reference item.
 */
public int newConstantDynamic(final String name,final String descriptor,final Handle bootstrapMethodHandle,final Object... bootstrapMethodArguments){
  return symbolTable.addConstantDynamic(name,descriptor,bootstrapMethodHandle,bootstrapMethodArguments).index;
}
