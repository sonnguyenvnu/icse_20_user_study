/** 
 * Pushes int value in an optimal way.
 */
public static void pushInt(final MethodVisitor mv,final int value){
  if (value <= 5) {
    mv.visitInsn(ICONST_0 + value);
  }
 else   if (value <= Byte.MAX_VALUE) {
    mv.visitIntInsn(BIPUSH,value);
  }
 else {
    mv.visitIntInsn(SIPUSH,value);
  }
}
