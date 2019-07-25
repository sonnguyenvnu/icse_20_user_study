@JsonCreator static ContainerCreation create(@JsonProperty("Id") final String id,@JsonProperty("Warnings") final List<String> warnings){
  return builder().id(id).warnings(warnings).build();
}
