/** 
 * Write data to a sheet
 * @param data  Data to be written
 * @param sheet Write to this sheet
 * @param table Write to this table
 * @return this
 */
public ExcelWriter write0(List<List<String>> data,Sheet sheet,Table table){
  excelBuilder.addContent(data,sheet,table);
  return this;
}
