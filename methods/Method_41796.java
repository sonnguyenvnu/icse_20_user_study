private static void writeStringKey(final Object key,final ObjectOutputStream oos) throws IOException {
  oos.writeObject(key);
}
