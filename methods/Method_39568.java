/** 
 * Adds an invokedynamic reference to the constant pool of the class being build. Does nothing if the constant pool already contains a similar item. <i>This method is intended for  {@link Attribute} sub classes, and is normally not needed by class generators or adapters.</i>
 * @param name name of the invoked method.
 * @param descriptor descriptor of the invoke method.
 * @param bootstrapMethodHandle the bootstrap method.
 * @param bootstrapMethodArguments the bootstrap method constant arguments.
 * @return the index of a new or already existing invokedynamic reference item.
 */
public int newInvokeDynamic(final String name,final String descriptor,final Handle bootstrapMethodHandle,final Object... bootstrapMethodArguments){
  return symbolTable.addConstantInvokeDynamic(name,descriptor,bootstrapMethodHandle,bootstrapMethodArguments).index;
}
