/** 
 * Performs either a numeric cast or a type cast. <h3>Numeric Casts</h3> Converts a primitive to a different representation. Numeric casts may be lossy. For example, converting the double  {@code 1.8d} to an integeryields  {@code 1}, losing the fractional part. Converting the integer {@code 0x12345678} to a short yields {@code 0x5678}, losing the high bytes. The following numeric casts are supported: <p><table border="1" summary="Supported Numeric Casts"> <tr><th>From</th><th>To</th></tr> <tr><td>int</td><td>byte, char, short, long, float, double</td></tr> <tr><td>long</td><td>int, float, double</td></tr> <tr><td>float</td><td>int, long, double</td></tr> <tr><td>double</td><td>int, long, float</td></tr> </table> <p>For some primitive conversions it will be necessary to chain multiple cast operations. For example, to go from float to short one would first cast float to int and then int to short. <p>Numeric casts never throw  {@link ClassCastException}. <h3>Type Casts</h3> Checks that a reference value is assignable to the target type. If it is assignable it is copied to the target local. If it is not assignable a {@link ClassCastException} is thrown.
 */
public void cast(Local<?> target,Local<?> source){
  if (source.getType().ropType.isReference()) {
    addInstruction(new ThrowingCstInsn(Rops.CHECK_CAST,sourcePosition,RegisterSpecList.make(source.spec()),catches,target.type.constant));
    moveResult(target,true);
  }
 else {
    addInstruction(new PlainInsn(getCastRop(source.type.ropType,target.type.ropType),sourcePosition,target.spec(),source.spec()));
  }
}
