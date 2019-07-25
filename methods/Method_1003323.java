/** 
 * This method is called for each triggered action. The method is called immediately when the operation occurred (before it is committed). A transaction rollback will also rollback the operations that were done within the trigger, if the operations occurred within the same database. If the trigger changes state outside the database, a rollback trigger should be used. <p> The row arrays contain all columns of the table, in the same order as defined in the table. </p> <p> The default implementation calls the fire method with the ResultSet parameters. </p>
 * @param conn a connection to the database
 * @param oldRow the old row, or null if no old row is available (forINSERT)
 * @param newRow the new row, or null if no new row is available (forDELETE)
 * @throws SQLException if the operation must be undone
 */
@Override public void fire(Connection conn,Object[] oldRow,Object[] newRow) throws SQLException {
  fire(conn,wrap(oldResultSet,oldSource,oldRow),wrap(newResultSet,newSource,newRow));
}
