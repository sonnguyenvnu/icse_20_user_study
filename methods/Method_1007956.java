@JsonCreator static VolumeOptions create(@JsonProperty("NoCopy") final Boolean noCopy,@JsonProperty("Labels") final Map<String,String> labels,@JsonProperty("DriverConfig") final Driver driverConfig){
  return builder().noCopy(noCopy).labels(labels).driverConfig(driverConfig).build();
}
