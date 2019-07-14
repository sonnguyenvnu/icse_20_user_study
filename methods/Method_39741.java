/** 
 * Creates new array.
 */
public static void newArray(final MethodVisitor mv,final Class componentType){
  if (componentType == int.class) {
    mv.visitIntInsn(NEWARRAY,T_INT);
    return;
  }
  if (componentType == long.class) {
    mv.visitIntInsn(NEWARRAY,T_LONG);
    return;
  }
  if (componentType == float.class) {
    mv.visitIntInsn(NEWARRAY,T_FLOAT);
    return;
  }
  if (componentType == double.class) {
    mv.visitIntInsn(NEWARRAY,T_DOUBLE);
    return;
  }
  if (componentType == byte.class) {
    mv.visitIntInsn(NEWARRAY,T_BYTE);
    return;
  }
  if (componentType == short.class) {
    mv.visitIntInsn(NEWARRAY,T_SHORT);
    return;
  }
  if (componentType == boolean.class) {
    mv.visitIntInsn(NEWARRAY,T_BOOLEAN);
    return;
  }
  if (componentType == char.class) {
    mv.visitIntInsn(NEWARRAY,T_CHAR);
    return;
  }
  mv.visitTypeInsn(ANEWARRAY,AsmUtil.typeToSignature(componentType));
}
