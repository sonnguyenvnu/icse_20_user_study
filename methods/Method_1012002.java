@Override public void save(@NotNull DataOutput out,SNodeEntry value) throws IOException {
  if (value == null) {
    throw new NullPointerException("Shall not try to serialize null values");
  }
  ByteArrayOutputStream bos=new ByteArrayOutputStream(17 + 17 + 9);
  ModelOutputStream mos=new ModelOutputStream(bos);
  saveEntry(mos,value);
  mos.close();
  writeToken(out);
  byte[] bytes=bos.toByteArray();
  out.writeInt(bytes.length);
  out.write(bytes);
}
