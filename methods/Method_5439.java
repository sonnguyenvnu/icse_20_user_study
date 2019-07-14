@Override public boolean continueLoading(long positionUs){
  if (!childrenPendingPreparation.isEmpty()) {
    int childrenPendingPreparationSize=childrenPendingPreparation.size();
    for (int i=0; i < childrenPendingPreparationSize; i++) {
      childrenPendingPreparation.get(i).continueLoading(positionUs);
    }
    return false;
  }
 else {
    return compositeSequenceableLoader.continueLoading(positionUs);
  }
}
