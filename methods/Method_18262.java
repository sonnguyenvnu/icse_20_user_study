private static void addLayoutOutputIdToPositionsMap(LongSparseArray outputsIdToPositionMap,LayoutOutput layoutOutput,int position){
  if (outputsIdToPositionMap != null) {
    outputsIdToPositionMap.put(layoutOutput.getId(),position);
  }
}
