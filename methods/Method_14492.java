@JsonProperty("subgroups") @JsonView(JsonViews.NonSaveMode.class) @JsonInclude(Include.NON_EMPTY) public List<ColumnGroup> getSubGroups(){
  return subgroups;
}
