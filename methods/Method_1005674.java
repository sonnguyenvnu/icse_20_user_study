/** 
 * Copies the value in instance field  {@code fieldId} of {@code instance} to{@code target}.
 */
public <D,V>void iget(FieldId<D,? extends V> fieldId,Local<V> target,Local<D> instance){
  addInstruction(new ThrowingCstInsn(Rops.opGetField(target.type.ropType),sourcePosition,RegisterSpecList.make(instance.spec()),catches,fieldId.constant));
  moveResult(target,true);
}
