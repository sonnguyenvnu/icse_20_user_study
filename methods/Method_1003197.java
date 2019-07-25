/** 
 * Redo a change in a table.
 * @param tableId the object id of the table
 * @param row the row
 * @param add true if the record is added, false if deleted
 */
void redo(int tableId,Row row,boolean add){
  if (tableId == META_TABLE_ID) {
    if (add) {
      addMeta(row,pageStoreSession,true);
    }
 else {
      removeMeta(row);
    }
  }
  Index index=metaObjects.get(tableId);
  if (index == null) {
    throw DbException.throwInternalError("Table not found: " + tableId + " " + row + " " + add);
  }
  Table table=index.getTable();
  if (add) {
    table.addRow(pageStoreSession,row);
  }
 else {
    table.removeRow(pageStoreSession,row);
  }
}
