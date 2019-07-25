@JsonCreator static ContainerUpdate create(@JsonProperty("Warnings") final List<String> warnings){
  return builder().warnings(warnings).build();
}
