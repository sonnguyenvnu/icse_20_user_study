protected ReplicationStoreMeta format(Object payload){
  ByteArrayOutputStreamPayload data=(ByteArrayOutputStreamPayload)payload;
  String buff=new String(data.getBytes(),Codec.defaultCharset);
  logger.info("[format]{}",buff);
  ReplicationStoreMeta meta=null;
  meta=JSON.parseObject(buff,ReplicationStoreMeta.class);
  if (valid(meta)) {
    return meta;
  }
 else {
    throw new XpipeRuntimeException("[format][wrong meta]" + meta);
  }
}
