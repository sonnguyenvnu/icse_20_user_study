/** 
 * Stores element on stack into an array.
 */
public static void storeIntoArray(final MethodVisitor mv,final Class componentType){
  if (componentType == int.class) {
    mv.visitInsn(IASTORE);
    return;
  }
  if (componentType == long.class) {
    mv.visitInsn(LASTORE);
    return;
  }
  if (componentType == float.class) {
    mv.visitInsn(FASTORE);
    return;
  }
  if (componentType == double.class) {
    mv.visitInsn(DASTORE);
    return;
  }
  if (componentType == byte.class) {
    mv.visitInsn(BASTORE);
    return;
  }
  if (componentType == short.class) {
    mv.visitInsn(SASTORE);
    return;
  }
  if (componentType == boolean.class) {
    mv.visitInsn(BASTORE);
    return;
  }
  if (componentType == char.class) {
    mv.visitInsn(CASTORE);
    return;
  }
  mv.visitInsn(AASTORE);
}
