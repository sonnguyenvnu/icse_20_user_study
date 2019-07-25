/** 
 * Encodes the instruction. Writes the result in the outputStream. Needs one operand of OperandType.REGISTER or OperandType.MEMORY_REG16
 * @param out encoded bytes will be written here
 */
@Override public void encode(ByteArrayOutputStream out,Operand o1,int currentLine) throws AssemblyException {
  String mnemonic=getMnemonic().toLowerCase();
  Character familyOpCode=mnemonicFamilyOpCodeMap.get(mnemonic);
  if (familyOpCode == null) {
    throw new InvalidMnemonicException(getMnemonic(),currentLine);
  }
  if (!operandValid(o1)) {
    throw new IllegalOperandException("Illegal operand combination: " + o1.getType() + " (none)",currentLine);
  }
  MachineCode code=new MachineCode();
  code.writeOpcode(getOpCode());
  code.writeSourceOperand(Operand.IMMEDIATE_VALUE);
  code.appendWord(familyOpCode);
  if (o1.getType() == OperandType.REGISTER16 || o1.getType() == OperandType.MEMORY_REG16) {
    code.writeDestinationOperand(o1.getValue());
  }
 else {
    code.writeDestinationOperand(o1.getValue());
    code.appendWord((char)o1.getData());
  }
  for (  byte b : code.bytes()) {
    out.write(b);
  }
}
