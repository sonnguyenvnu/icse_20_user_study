/** 
 * Returns a  {@link org.janusgraph.core.log.LogProcessorFramework} for processing transaction log entriesagainst the provided graph instance.
 * @param graph
 * @return
 */
public static LogProcessorFramework openTransactionLog(JanusGraph graph){
  return new StandardLogProcessorFramework((StandardJanusGraph)graph);
}
