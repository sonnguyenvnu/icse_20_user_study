/** 
 * Returns all records matching the given SQL query. <p> The query should only contain the "where" part of the SQL query, and optinally the "order by" part. "Group by" is not allowed. If no matching records are found, returns an empty list.
 */
@NonNull public List<T> findAll(@NonNull String query,@NonNull String... params){
  try (Cursor c=db.query(buildSelectQuery() + query,params)){
    return cursorToMultipleRecords(c);
  }
 }
