@Override public StandardTransactionId read(ScanBuffer buffer){
  return new StandardTransactionId(serializer.readObjectNotNull(buffer,String.class),serializer.readObjectNotNull(buffer,Long.class),serializer.readObjectNotNull(buffer,Instant.class));
}
