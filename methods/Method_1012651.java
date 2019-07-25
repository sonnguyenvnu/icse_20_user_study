/** 
 * Removes an entry or entries based on its FILENAME. If  {@code useLike} is{@code true},  {@code filename} must be properly escaped.
 * @see Tables#sqlLikeEscape(String)
 * @param filename the filename to remove
 * @param useLike {@code true} if {@code LIKE} should be used as the compareoperator,  {@code false} if {@code =} should be used.
 */
public static void remove(final String filename,boolean useLike){
  try (Connection connection=database.getConnection()){
    String query="DELETE FROM " + TABLE_NAME + " WHERE FILENAME " + (useLike ? "LIKE " : "= ") + sqlQuote(filename);
    TABLE_LOCK.writeLock().lock();
    try (Statement statement=connection.createStatement()){
      int rows=statement.executeUpdate(query);
      LOGGER.trace("Removed entries {} in " + TABLE_NAME + " for filename \"{}\"",rows,filename);
    }
  finally {
      TABLE_LOCK.writeLock().unlock();
    }
  }
 catch (  SQLException e) {
    LOGGER.error("Database error while removing entries from " + TABLE_NAME + " for \"{}\": {}",filename,e.getMessage());
    LOGGER.trace("",e);
  }
}
