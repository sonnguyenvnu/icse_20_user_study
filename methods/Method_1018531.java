public byte[] cat(Multihash hash) throws IOException {
  return retrieve("cat/" + hash);
}
