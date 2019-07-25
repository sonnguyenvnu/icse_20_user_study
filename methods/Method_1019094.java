public String decompile(){
  Map<String,String> options=ConfCFR.instance().toStringMap();
  if (mn != null) {
    options.put("methodname",mn.name);
  }
  Lookup lookup=new Lookup(cn,mn == null);
  SourceInput source=new SourceInput(lookup);
  SinkFactory sink=new SinkFactory();
  CfrDriver driver=new CfrDriver.Builder().withClassFileSource(source).withOutputSink(sink).withOptions(options).build();
  driver.analyse(Collections.singletonList(cn.name));
  String decompilation=sink.getDecompilation();
  if (decompilation.startsWith("/")) {
    decompilation=decompilation.substring(decompilation.indexOf("*/") + 3);
  }
  return decompilation;
}
