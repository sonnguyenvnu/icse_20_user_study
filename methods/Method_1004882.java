public boolean contains(final String auth){
  return this.auths.contains(new ArrayByteSequence(auth));
}
