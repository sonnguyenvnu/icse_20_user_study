/** 
 * @param allowNullChkBox
 * @param defaultValue
 * @param nullValueNull
 * @param col
 * @param rowValue
 * @param quote
 * @param fieldCount
 */
public void handleNullField(boolean allowNullChkBox,String defaultValue,boolean nullValueNull,String col,StringBuilder rowValue,boolean quote){
  if (allowNullChkBox) {
    if (defaultValue != null && !defaultValue.isEmpty()) {
      if (quote) {
        rowValue.append("'" + defaultValue + "'");
      }
 else {
        rowValue.append(defaultValue);
      }
    }
 else {
      if (nullValueNull) {
        rowValue.append("null");
      }
 else {
        throw new SqlExporterException("Null value not allowed for Field :" + col);
      }
    }
  }
 else {
    if (defaultValue != null && !defaultValue.isEmpty()) {
      if (quote) {
        rowValue.append("'" + defaultValue + "'");
      }
 else {
        rowValue.append(defaultValue);
      }
    }
 else {
      throw new SqlExporterException("Null value not allowed for Field :" + col);
    }
  }
}
