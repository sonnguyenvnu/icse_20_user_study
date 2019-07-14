@JsonCreator static public Row deserialize(@JsonProperty(STARRED) boolean starred,@JsonProperty(FLAGGED) boolean flagged,@JsonProperty("cells") List<Cell> cells){
  if (cells == null) {
    cells=new ArrayList<>();
  }
  return new Row(cells,flagged,starred);
}
