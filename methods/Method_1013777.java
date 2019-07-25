public static TunnelTrafficResult parse(Object obj){
  if (!obj.getClass().isArray()) {
    throw new XpipeRuntimeException("Illegal TunnelTrafficResult meta data, should be an array");
  }
  Object[] metaData=(Object[])obj;
  if (!(metaData[0] instanceof String)) {
    throw new XpipeRuntimeException("Illegal TunnelTrafficResult meta data, first element should be string");
  }
  Object[] frontMeta=(Object[])metaData[1];
  Object[] backMeta=(Object[])metaData[2];
  return new TunnelTrafficResult((String)metaData[0],SessionTrafficResult.parseFromArray(frontMeta),SessionTrafficResult.parseFromArray(backMeta));
}
