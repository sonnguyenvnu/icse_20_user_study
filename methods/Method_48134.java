/** 
 * Returns a  {@link TransactionRecovery} process for recovering partially failed transactions. The recovery processwill start processing the write-ahead transaction log at the specified transaction time.
 * @param graph
 * @param start
 * @return
 */
public static TransactionRecovery startTransactionRecovery(JanusGraph graph,Instant start){
  return new StandardTransactionLogProcessor((StandardJanusGraph)graph,start);
}
