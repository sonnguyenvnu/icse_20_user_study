/** 
 * Converts <code>Byte</code> object to a <code>byte</code>.
 */
public static void byteValue(final MethodVisitor mv){
  mv.visitTypeInsn(CHECKCAST,SIGNATURE_JAVA_LANG_BYTE);
  mv.visitMethodInsn(INVOKEVIRTUAL,SIGNATURE_JAVA_LANG_BYTE,"byteValue","()B",false);
}
