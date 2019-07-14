@Override public void open(DataSpec dataSpec) throws IOException {
  if (dataSpec.length == C.LENGTH_UNSET) {
    stream=new ByteArrayOutputStream();
  }
 else {
    Assertions.checkArgument(dataSpec.length <= Integer.MAX_VALUE);
    stream=new ByteArrayOutputStream((int)dataSpec.length);
  }
}
