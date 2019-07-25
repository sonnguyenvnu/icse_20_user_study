public Multihash get(){
  if (!isPresent())   throw new IllegalStateException("hash not present");
  return hash;
}
