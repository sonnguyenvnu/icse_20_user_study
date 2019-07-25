public static <T>byte[] serialise(final ToBytesSerialiser<T> serialiser,final T value) throws SerialisationException {
  final byte[] valueBytes=getValueBytes(serialiser,value);
  return serialise(valueBytes);
}
