@SuppressWarnings("unchecked") private void read(Object input) throws YAMLProcessorException {
  try {
    if (null == input) {
      root=new LinkedHashMap<>();
    }
 else {
      root=new LinkedHashMap<>((Map<String,Object>)input);
    }
  }
 catch (  ClassCastException e) {
    throw new YAMLProcessorException("Root document must be an key-value structure");
  }
}
