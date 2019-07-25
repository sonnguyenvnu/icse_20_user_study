@JsonCreator static Device create(@JsonProperty("PathOnHost") final String pathOnHost,@JsonProperty("PathInContainer") final String pathInContainer,@JsonProperty("CgroupPermissions") final String cgroupPermissions){
  return builder().pathOnHost(pathOnHost).pathInContainer(pathInContainer).cgroupPermissions(cgroupPermissions).build();
}
