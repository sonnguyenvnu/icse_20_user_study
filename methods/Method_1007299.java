/** 
 * This method is equivalent to <code>descriptor()</code>. If this attribute represents a LocalVariableTypeTable attribute, this method should be used instead of <code>descriptor()</code> since the method name is more appropriate. <p>To parse the string, call <code>toFieldSignature(String)</code> in <code>SignatureAttribute</code>.
 * @param i         the i-th entry.
 * @see #descriptor(int)
 * @see SignatureAttribute#toFieldSignature(String)
 */
public String signature(int i){
  return descriptor(i);
}
