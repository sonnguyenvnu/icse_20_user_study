/** 
 * Converts <code>Integer</code> object to an <code>int</code>.
 */
public static void intValue(final MethodVisitor mv){
  mv.visitTypeInsn(CHECKCAST,SIGNATURE_JAVA_LANG_INTEGER);
  mv.visitMethodInsn(INVOKEVIRTUAL,SIGNATURE_JAVA_LANG_INTEGER,"intValue","()I",false);
}
