/** 
 * Invoke this method at the end of the subclass constructor to initiate the queries.
 */
final void start(){
  checkState(!started);
  started=true;
  eventLoop.execute(this::sendQueries);
}
