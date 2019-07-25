@Override public Long deserialise(final byte[] bytes) throws SerialisationException {
  return CompactRawSerialisationUtils.readLong(bytes);
}
