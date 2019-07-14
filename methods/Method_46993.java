/** 
 * Return table string for corresponding  {@link Operation}
 */
private String getTableForOperation(Operation operation){
switch (operation) {
case HISTORY:
    return TABLE_HISTORY;
case HIDDEN:
  return TABLE_HIDDEN;
case LIST:
return TABLE_LIST;
case GRID:
return TABLE_GRID;
case BOOKMARKS:
return TABLE_BOOKMARKS;
case SMB:
return TABLE_SMB;
case SFTP:
return TABLE_SFTP;
default :
return null;
}
}
