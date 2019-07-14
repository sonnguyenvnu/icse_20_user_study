public static boolean indexCoversOrder(MixedIndexType index,OrderList orders){
  for (int i=0; i < orders.size(); i++) {
    if (!index.indexesKey(orders.getKey(i)))     return false;
  }
  return true;
}
