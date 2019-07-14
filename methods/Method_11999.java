private void writeObject(ObjectOutputStream s) throws IOException {
  SerializedForm serializedForm=new SerializedForm(this);
  serializedForm.serialize(s);
}
