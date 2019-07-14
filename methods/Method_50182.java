@SchemaModification(addField="localTime",onType=Timestamp.class) String localTime(Timestamp timestamp,@Arg("timezone") String timezone){
  return Instant.ofEpochSecond(timestamp.getSeconds()).atZone(ZoneId.of(timezone)).toString();
}
