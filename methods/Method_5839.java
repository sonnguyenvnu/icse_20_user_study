/** 
 * Constructs a NAL unit consisting of the NAL start code followed by the specified data.
 * @param data An array containing the data that should follow the NAL start code.
 * @param offset The start offset into {@code data}.
 * @param length The number of bytes to copy from {@code data}
 * @return The constructed NAL unit.
 */
public static byte[] buildNalUnit(byte[] data,int offset,int length){
  byte[] nalUnit=new byte[length + NAL_START_CODE.length];
  System.arraycopy(NAL_START_CODE,0,nalUnit,0,NAL_START_CODE.length);
  System.arraycopy(data,offset,nalUnit,NAL_START_CODE.length,length);
  return nalUnit;
}
