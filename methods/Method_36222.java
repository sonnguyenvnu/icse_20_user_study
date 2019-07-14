@JsonIgnore @Override public boolean isMultipart(){
  return (multiparts != null && multiparts.size() > 0);
}
