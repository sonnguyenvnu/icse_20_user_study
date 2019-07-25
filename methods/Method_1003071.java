/** 
 * Update query statistics.
 * @param sqlStatement the statement being executed
 * @param executionTimeNanos the time in nanoseconds the query/update tookto execute
 * @param rowCount the query or update row count
 */
public synchronized void update(String sqlStatement,long executionTimeNanos,int rowCount){
  QueryEntry entry=map.get(sqlStatement);
  if (entry == null) {
    entry=new QueryEntry(sqlStatement);
    map.put(sqlStatement,entry);
  }
  entry.update(executionTimeNanos,rowCount);
  if (map.size() > maxQueryEntries * 1.5f) {
    ArrayList<QueryEntry> list=new ArrayList<>(map.values());
    Collections.sort(list,QUERY_ENTRY_COMPARATOR);
    HashSet<QueryEntry> oldestSet=new HashSet<>(list.subList(0,list.size() / 3));
    for (Iterator<Entry<String,QueryEntry>> it=map.entrySet().iterator(); it.hasNext(); ) {
      Entry<String,QueryEntry> mapEntry=it.next();
      if (oldestSet.contains(mapEntry.getValue())) {
        it.remove();
      }
    }
  }
}
