@Override public SerializerCache snapshot(){
  return new SerializerCache(_sharedMap.snapshot());
}
