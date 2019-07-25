@Override public ByteBuf apply(MetricRegistry metricRegistry) throws Exception {
  ByteBuf byteBuf=byteBufAllocator.ioBuffer();
  try {
    OutputStream out=new ByteBufOutputStream(byteBuf);
    mapper.writeValue(out,metricRegistry);
    return byteBuf;
  }
 catch (  Exception e) {
    byteBuf.release();
    throw e;
  }
}
