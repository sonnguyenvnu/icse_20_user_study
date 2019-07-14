private void write(Project project,Column column,JsonGenerator writer) throws IOException {
  NumericBinIndex columnIndex=getBinIndex(project,column);
  if (columnIndex != null) {
    writer.writeStringField("name",column.getName());
    boolean is_numeric=columnIndex.isNumeric();
    writer.writeBooleanField("is_numeric",is_numeric);
    writer.writeNumberField("numeric_row_count",columnIndex.getNumericRowCount());
    writer.writeNumberField("non_numeric_row_count",columnIndex.getNonNumericRowCount());
    writer.writeNumberField("error_row_count",columnIndex.getErrorRowCount());
    writer.writeNumberField("blank_row_count",columnIndex.getBlankRowCount());
    if (is_numeric) {
      writer.writeNumberField("min",columnIndex.getMin());
      writer.writeNumberField("max",columnIndex.getMax());
      writer.writeNumberField("step",columnIndex.getStep());
    }
  }
 else {
    writer.writeStringField("error","error finding numeric information on the '" + column.getName() + "' column");
  }
}
