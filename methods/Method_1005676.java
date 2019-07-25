/** 
 * Copies the value in the static field  {@code fieldId} to {@code target}.
 */
public <V>void sget(FieldId<?,? extends V> fieldId,Local<V> target){
  addInstruction(new ThrowingCstInsn(Rops.opGetStatic(target.type.ropType),sourcePosition,RegisterSpecList.EMPTY,catches,fieldId.constant));
  moveResult(target,true);
}
