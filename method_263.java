public TiffContents _XXXXX_(final ByteSource byteSource,final Map<String,Object> params,final FormatCompliance formatCompliance) throws ImageReadException, IOException {
  final Collector collector=new Collector(params);
  read(byteSource,params,formatCompliance,collector);
  return collector.getContents();
}