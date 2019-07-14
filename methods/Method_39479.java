/** 
 * Converts <code>Double</code> object to a <code>double</code>.
 */
public static void doubleValue(final MethodVisitor mv){
  mv.visitTypeInsn(CHECKCAST,SIGNATURE_JAVA_LANG_DOUBLE);
  mv.visitMethodInsn(INVOKEVIRTUAL,SIGNATURE_JAVA_LANG_DOUBLE,"doubleValue","()D",false);
}
