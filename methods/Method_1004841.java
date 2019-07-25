/** 
 * get a single row from the replicator and pass it to the producer or bootstrapper. This is the top-level function in the run-loop.
 */
public void work() throws Exception {
  RowMap row=getRow();
  if (row == null)   return;
  rowCounter.inc();
  rowMeter.mark();
  if (scripting != null)   scripting.invoke(row);
  processRow(row);
}
