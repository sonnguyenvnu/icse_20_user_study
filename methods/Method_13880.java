/** 
 * Insert a set of rows and optionally return the IDs of the new rows.
 * @param service a Fusiontables object
 * @param sql SQL statement to do the inserts
 * @param returnIds true to return the IDs of the newly inserted rows
 * @return
 * @throws IOException
 */
static Long insertRows(Fusiontables service,String tableId,AbstractInputStreamContent mediaContent) throws IOException {
  ImportRows importRows=service.table().importRows(tableId,mediaContent);
  importRows.setIsStrict(false);
  FusiontablesImport response=importRows.execute();
  return response.getNumRowsReceived();
}
