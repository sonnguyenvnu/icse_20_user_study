/** 
 * Clears and re-populates the test item map if we are in e2e test mode.
 */
private void processTestOutputs(LayoutState layoutState){
  if (mTestItemMap == null) {
    return;
  }
  mTestItemMap.clear();
  for (int i=0, size=layoutState.getTestOutputCount(); i < size; i++) {
    final TestOutput testOutput=layoutState.getTestOutputAt(i);
    final long hostMarker=testOutput.getHostMarker();
    final long layoutOutputId=testOutput.getLayoutOutputId();
    final MountItem mountItem=layoutOutputId == -1 ? null : mIndexToItemMap.get(layoutOutputId);
    final TestItem testItem=new TestItem();
    testItem.setHost(hostMarker == -1 ? null : mHostsByMarker.get(hostMarker));
    testItem.setBounds(testOutput.getBounds());
    testItem.setTestKey(testOutput.getTestKey());
    testItem.setContent(mountItem == null ? null : mountItem.getContent());
    final Deque<TestItem> items=mTestItemMap.get(testOutput.getTestKey());
    final Deque<TestItem> updatedItems=items == null ? new LinkedList<TestItem>() : items;
    updatedItems.add(testItem);
    mTestItemMap.put(testOutput.getTestKey(),updatedItems);
  }
}
