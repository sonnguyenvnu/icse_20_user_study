/** 
 * Creates a  {@link GraphBinding} associated with the default {@link DataFlowGraph} instance.
 */
public static GraphBinding create(){
  return new GraphBinding(DataFlowGraph.getInstance());
}
