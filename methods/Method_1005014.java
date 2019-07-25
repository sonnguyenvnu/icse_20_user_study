@Override public byte[] serialise(final ItemsUnion<String> union) throws SerialisationException {
  return union.getResult().toByteArray(SERIALISER);
}
