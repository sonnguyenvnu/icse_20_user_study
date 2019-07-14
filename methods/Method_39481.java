/** 
 * Converts <code>Short</code> object to a <code>short</code>.
 */
public static void shortValue(final MethodVisitor mv){
  mv.visitTypeInsn(CHECKCAST,SIGNATURE_JAVA_LANG_SHORT);
  mv.visitMethodInsn(INVOKEVIRTUAL,SIGNATURE_JAVA_LANG_SHORT,"shortValue","()S",false);
}
