/** 
 * Converts <code>Long</code> object to a <code>long</code>.
 */
public static void longValue(final MethodVisitor mv){
  mv.visitTypeInsn(CHECKCAST,SIGNATURE_JAVA_LANG_LONG);
  mv.visitMethodInsn(INVOKEVIRTUAL,SIGNATURE_JAVA_LANG_LONG,"longValue","()J",false);
}
