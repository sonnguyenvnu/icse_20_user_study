public static Object deserializeFromString(String key) throws IOException, ClassNotFoundException {
  if (key.length() >= 1 && key.charAt(0) == MARKER) {
    ObjectInputStream ois=new ObjectInputStream(new StringSerializedObjectInputStream(key));
    return ois.readObject();
  }
  return key;
}
