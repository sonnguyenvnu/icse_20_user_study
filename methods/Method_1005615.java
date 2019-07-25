/** 
 * Helper to produce the first bit of output for each instruction.
 * @param offset the offset to the start of the instruction
 */
private String header(int offset){
  int opcode=bytes.getUnsignedByte(offset);
  String name=ByteOps.opName(opcode);
  if (opcode == ByteOps.WIDE) {
    opcode=bytes.getUnsignedByte(offset + 1);
    name+=" " + ByteOps.opName(opcode);
  }
  return Hex.u2(offset) + ": " + name;
}
