private StaticBuffer getSettingKey(String identifier){
  DataOutput out=manager.serializer.getDataOutput(4 + 2 + identifier.length());
  out.putInt(SYSTEM_PARTITION_ID);
  out.writeObjectNotNull(identifier);
  return out.getStaticBuffer();
}
