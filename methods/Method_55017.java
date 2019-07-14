/** 
 * Initializes this struct with the specified values. 
 */
public JNINativeMethod set(ByteBuffer name,ByteBuffer signature,long fnPtr){
  name(name);
  signature(signature);
  fnPtr(fnPtr);
  return this;
}
