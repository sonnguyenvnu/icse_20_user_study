private void addWriteOrderDeque(){
  if (Feature.usesWriteOrderDeque(context.parentFeatures) || !Feature.usesWriteOrderDeque(context.generateFeatures)) {
    return;
  }
  addDeque(WRITE_ORDER_DEQUE,"writeOrderDeque");
}
