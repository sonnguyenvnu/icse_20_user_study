public boolean contains(final byte[] auth){
  return this.auths.contains(new ArrayByteSequence(auth));
}
