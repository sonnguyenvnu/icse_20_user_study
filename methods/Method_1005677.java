/** 
 * Copies the value in  {@code source} to the static field {@code fieldId}.
 */
public <V>void sput(FieldId<?,V> fieldId,Local<? extends V> source){
  addInstruction(new ThrowingCstInsn(Rops.opPutStatic(source.type.ropType),sourcePosition,RegisterSpecList.make(source.spec()),catches,fieldId.constant));
}
