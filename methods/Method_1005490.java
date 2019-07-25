public void validate(){
  if (srcField == null) {
    MappingUtils.throwMappingException("src field must be specified");
  }
  if (destField == null) {
    MappingUtils.throwMappingException("dest field must be specified");
  }
}
