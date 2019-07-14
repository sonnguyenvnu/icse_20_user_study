@Override public MappingsLoader mappingsLoader(){
  return new JsonFileMappingsSource(filesRoot().child("mappings"));
}
