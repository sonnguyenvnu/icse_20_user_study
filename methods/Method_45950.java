protected Byte parseSerializeType(String serialization){
  Byte serializeType=SerializerFactory.getCodeByAlias(serialization);
  if (serializeType == null) {
    throw new SofaRpcRuntimeException("Unsupported serialization type: " + serialization);
  }
  return serializeType;
}
