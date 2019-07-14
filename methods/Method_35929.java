@JsonIgnore public boolean specifiesTextBodyContent(){
  return body.isPresent() && !body.isBinary();
}
