@JsonIgnore public boolean specifiesBodyContent(){
  return body.isPresent();
}
