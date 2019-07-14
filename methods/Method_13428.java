@JsonIgnore public List<MediaType> getConsumeMediaTypes(){
  return toMediaTypes(consumes);
}
