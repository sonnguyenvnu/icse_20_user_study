public static String stringify(byte[] code){
  int index=0;
  StringBuilder sb=new StringBuilder();
  while (index < code.length) {
    final byte opCode=code[index];
    OpCode op=OpCode.code(opCode);
    if (op == null) {
      sb.append(" <UNKNOWN>: ").append(0xFF & opCode).append(" ");
      index++;
      continue;
    }
    if (op.name().startsWith("PUSH")) {
      sb.append(' ').append(op.name()).append(' ');
      int nPush=op.val() - OpCode.PUSH1.val() + 1;
      byte[] data=Arrays.copyOfRange(code,index + 1,index + nPush + 1);
      BigInteger bi=new BigInteger(1,data);
      sb.append("0x").append(bi.toString(16)).append(" ");
      index+=nPush + 1;
    }
 else {
      sb.append(' ').append(op.name());
      index++;
    }
  }
  return sb.toString();
}
