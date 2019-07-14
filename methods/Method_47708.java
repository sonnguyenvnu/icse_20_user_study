/** 
 * Changes the order of habits on the adapter. <p> Note that this only has effect on the adapter cache. The database is not modified, and the change is lost when the cache is refreshed. This method is useful for making the ListView more responsive: while we wait for the database operation to finish, the cache can be modified to reflect the changes immediately.
 * @param from the habit that should be moved
 * @param to   the habit that currently occupies the desired position
 */
public void performReorder(int from,int to){
  cache.reorder(from,to);
}
