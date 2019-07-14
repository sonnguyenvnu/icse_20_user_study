private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException {
  serializedForm=SerializedForm.deserialize(s);
}
