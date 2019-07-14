@SchemaModification(addField="iso",onType=Timestamp.class) String isoString(Timestamp timestamp){
  return Instant.ofEpochSecond(timestamp.getSeconds()).toString();
}
