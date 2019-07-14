/** 
 * Adds a handle to the constant pool of the class being build. Does nothing if the constant pool already contains a similar item. <i>This method is intended for  {@link Attribute} sub classes,and is normally not needed by class generators or adapters.</i>
 * @param tag the kind of this handle. Must be {@link Opcodes#H_GETFIELD},  {@link Opcodes#H_GETSTATIC},  {@link Opcodes#H_PUTFIELD},  {@link Opcodes#H_PUTSTATIC},  {@link Opcodes#H_INVOKEVIRTUAL},  {@link Opcodes#H_INVOKESTATIC},  {@link Opcodes#H_INVOKESPECIAL}, {@link Opcodes#H_NEWINVOKESPECIAL} or {@link Opcodes#H_INVOKEINTERFACE}.
 * @param owner the internal name of the field or method owner class.
 * @param name the name of the field or method.
 * @param descriptor the descriptor of the field or method.
 * @param isInterface true if the owner is an interface.
 * @return the index of a new or already existing method type reference item.
 */
public int newHandle(final int tag,final String owner,final String name,final String descriptor,final boolean isInterface){
  return symbolTable.addConstantMethodHandle(tag,owner,name,descriptor,isInterface).index;
}
