/** 
 * @see LithoViewTestHelper#findTestItems(LithoView,String)
 */
@VisibleForTesting(otherwise=VisibleForTesting.PRIVATE) Deque<TestItem> findTestItems(String testKey){
  if (mTestItemMap == null) {
    throw new UnsupportedOperationException("Trying to access TestItems while " + "ComponentsConfiguration.isEndToEndTestRun is false.");
  }
  final Deque<TestItem> items=mTestItemMap.get(testKey);
  return items == null ? new LinkedList<TestItem>() : items;
}
