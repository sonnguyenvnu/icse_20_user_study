@Override public void save(@NotNull DataOutput out,T value) throws IOException {
  ByteArrayOutputStream stream=new ByteArrayOutputStream();
  ObjectOutput output=new ObjectOutputStream(stream);
  output.writeObject(value);
  out.writeInt(stream.size());
  out.write(stream.toByteArray());
}
