@Override public String marshal(T item){
  try {
    return objectMapper.writeValueAsString(item);
  }
 catch (  JsonProcessingException e) {
    throw new ItemStreamException("Unable to marshal object " + item + " to Json",e);
  }
}
