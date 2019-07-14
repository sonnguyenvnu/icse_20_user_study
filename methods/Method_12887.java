/** 
 * Write data to a sheet
 * @param data  Data to be written
 * @param sheet Write to this sheet
 * @param table Write to this table
 * @return
 */
public ExcelWriter write1(List<List<Object>> data,Sheet sheet,Table table){
  excelBuilder.addContent(data,sheet,table);
  return this;
}
