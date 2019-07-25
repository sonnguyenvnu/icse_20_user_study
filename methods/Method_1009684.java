public Slice encode(){
  DynamicSliceOutput dynamicSliceOutput=new DynamicSliceOutput(4096);
  for (  VersionEditTag versionEditTag : VersionEditTag.values()) {
    versionEditTag.writeValue(dynamicSliceOutput,this);
  }
  return dynamicSliceOutput.slice();
}
