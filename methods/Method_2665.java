public void save(ObjectOutputStream out) throws IOException {
  out.writeObject(base);
  out.writeObject(check);
}
