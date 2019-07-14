@Override public Map<String,String> getMetadata(){
  return singletonMap("startup",this.timestamp.format(DateTimeFormatter.ISO_DATE_TIME));
}
