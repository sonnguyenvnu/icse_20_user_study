/** 
 * For tests, let's the testing environment explicitly provide a specific DataFlowGraph instance that can, for example, have a mocked TimingSource.
 */
@VisibleForTesting public static void setInstance(DataFlowGraph dataFlowGraph){
  sInstance=dataFlowGraph;
}
