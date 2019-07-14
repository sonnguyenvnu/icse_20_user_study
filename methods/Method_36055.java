public RecordSpecBuilder captureHeader(String key,Boolean caseInsensitive){
  headers.put(key,new CaptureHeadersSpec(caseInsensitive));
  return this;
}
