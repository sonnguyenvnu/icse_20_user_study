@JsonIgnore public boolean specifiesBodyFile(){
  return bodyFileName != null && body.isAbsent();
}
