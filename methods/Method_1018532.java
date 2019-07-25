public byte[] get(Multihash hash) throws IOException {
  return retrieve("get/" + hash);
}
