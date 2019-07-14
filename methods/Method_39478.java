/** 
 * Converts <code>Float</code> object to a <code>float</code>.
 */
public static void floatValue(final MethodVisitor mv){
  mv.visitTypeInsn(CHECKCAST,SIGNATURE_JAVA_LANG_FLOAT);
  mv.visitMethodInsn(INVOKEVIRTUAL,SIGNATURE_JAVA_LANG_FLOAT,"floatValue","()F",false);
}
