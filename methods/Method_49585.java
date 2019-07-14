private void setCFOptions(HColumnDescriptor columnDescriptor,int ttlInSeconds){
  if (null != compression && !compression.equals(COMPRESSION_DEFAULT))   compat.setCompression(columnDescriptor,compression);
  if (ttlInSeconds > 0)   columnDescriptor.setTimeToLive(ttlInSeconds);
}
