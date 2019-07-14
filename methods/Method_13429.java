@JsonIgnore public List<MediaType> getProduceMediaTypes(){
  return toMediaTypes(produces);
}
