static protected Serializable extractCell(OdfTableCell cell){
  Serializable value=null;
  String cellType=cell.getValueType();
  if ("boolean".equals(cellType)) {
    value=cell.getBooleanValue();
  }
 else   if ("float".equals(cellType)) {
    value=cell.getDoubleValue();
  }
 else   if ("date".equals(cellType)) {
    value=cell.getDateValue();
  }
 else   if ("currency".equals(cellType)) {
    value=cell.getCurrencyValue();
  }
 else   if ("percentage".equals(cellType)) {
    value=cell.getPercentageValue();
  }
 else   if ("string".equals(cellType)) {
    value=cell.getStringValue();
  }
 else   if (cellType == null) {
    value=cell.getDisplayText();
    if ("".equals(value)) {
      value=null;
    }
 else {
      logger.info("Null cell type with non-empty value: " + value);
    }
  }
 else {
    logger.info("Unexpected cell type " + cellType);
    value=cell.getDisplayText();
  }
  return value;
}
