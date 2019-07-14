@JsonIgnore public boolean specifiesBinaryBodyContent(){
  return (body.isPresent() && body.isBinary());
}
