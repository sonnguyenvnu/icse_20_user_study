/** 
 * Returns the  {@link TProtocolFactory} for the specified {@link SerializationFormat}.
 * @throws IllegalArgumentException if the specified {@link SerializationFormat} is not for Thrift
 */
public static TProtocolFactory get(SerializationFormat serializationFormat){
  requireNonNull(serializationFormat,"serializationFormat");
  if (serializationFormat == ThriftSerializationFormats.BINARY) {
    return BINARY;
  }
  if (serializationFormat == ThriftSerializationFormats.COMPACT) {
    return COMPACT;
  }
  if (serializationFormat == ThriftSerializationFormats.JSON) {
    return JSON;
  }
  if (serializationFormat == ThriftSerializationFormats.TEXT) {
    return TEXT;
  }
  throw new IllegalArgumentException("non-Thrift serializationFormat: " + serializationFormat);
}
