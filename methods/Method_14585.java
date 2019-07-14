@JsonCreator public static ColumnSplitOperation deserialize(@JsonProperty("engineConfig") EngineConfig engineConfig,@JsonProperty("columnName") String columnName,@JsonProperty("guessCellType") boolean guessCellType,@JsonProperty("removeOriginalColumn") boolean removeOriginalColumn,@JsonProperty("mode") String mode,@JsonProperty("separator") String separator,@JsonProperty("regex") Boolean regex,@JsonProperty("maxColumns") Integer maxColumns,@JsonProperty("fieldLengths") int[] fieldLengths){
  if ("separator".equals(mode)) {
    return new ColumnSplitOperation(engineConfig,columnName,guessCellType,removeOriginalColumn,separator,regex,maxColumns);
  }
 else {
    return new ColumnSplitOperation(engineConfig,columnName,guessCellType,removeOriginalColumn,fieldLengths);
  }
}
