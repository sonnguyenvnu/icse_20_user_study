public static void writeBuffer(DataOutput out,StaticBuffer buffer){
  VariableLong.writePositive(out,buffer.length());
  out.putBytes(buffer);
}
