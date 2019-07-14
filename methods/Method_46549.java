/** 
 * TXC?????????
 */
@Override public void init(){
  h2DbHelper.update("CREATE TABLE IF NOT EXISTS TXC_UNDO_LOG (" + "ID BIGINT NOT NULL AUTO_INCREMENT, " + "UNIT_ID VARCHAR(32) NOT NULL," + "GROUP_ID VARCHAR(64) NOT NULL," + "SQL_TYPE INT NOT NULL," + "ROLLBACK_INFO BLOB NOT NULL," + "CREATE_TIME CHAR(23) NOT NULL, " + "PRIMARY KEY(ID) )");
  log.info("Txc log table finish (H2 DATABASE)");
}
