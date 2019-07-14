@SchemaModification(addField="afterNow",onType=Timestamp.class) Boolean isAfterNow(Timestamp timestamp){
  return Instant.ofEpochSecond(timestamp.getSeconds()).isAfter(Instant.now());
}
