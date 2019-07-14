@JsonCreator static public TransposeColumnsIntoRowsOperation deserialize(@JsonProperty("combinedColumnName") String combinedColumnName,@JsonProperty("startColumnName") String startColumnName,@JsonProperty("columnCount") int columnCount,@JsonProperty("ignoreBlankCells") Boolean ignoreBlankCells,@JsonProperty("fillDown") Boolean fillDown,@JsonProperty("prependColumnName") boolean prependColumnName,@JsonProperty("separator") String separator,@JsonProperty("keyColumnName") String keyColumnName,@JsonProperty("valueColumnName") String valueColumnName){
  ignoreBlankCells=ignoreBlankCells == null ? true : ignoreBlankCells;
  fillDown=fillDown == null ? false : fillDown;
  if (combinedColumnName != null) {
    return new TransposeColumnsIntoRowsOperation(startColumnName,columnCount,ignoreBlankCells,fillDown,combinedColumnName,prependColumnName,separator);
  }
 else {
    return new TransposeColumnsIntoRowsOperation(startColumnName,columnCount,ignoreBlankCells,fillDown,keyColumnName,valueColumnName);
  }
}
