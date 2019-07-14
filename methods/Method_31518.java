/** 
 * Drops system versioning for this table if it is active.
 */
void dropSystemVersioningIfPresent() throws SQLException {
  if (jdbcTemplate.queryForInt("SELECT temporal_type FROM sys.tables WHERE object_id = OBJECT_ID('" + this + "', 'U')") == 2) {
    jdbcTemplate.execute("ALTER TABLE " + this + " SET (SYSTEM_VERSIONING = OFF)");
  }
}
