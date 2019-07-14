@Override protected void diffGroup(SparseArray<Card> added,SparseArray<Card> removed){
  for (int i=0, size=removed.size(); i < size; i++) {
    int key=removed.keyAt(i);
    Card card=removed.get(key);
    if (card != null) {
      try {
        card.removed();
      }
 catch (      Exception e) {
        if (card.serviceManager != null) {
          CellSupport cellSupport=card.serviceManager.getService(CellSupport.class);
          if (card.extras != null) {
            cellSupport.onException(card.extras.toString(),e);
          }
 else {
            cellSupport.onException(card.stringType,e);
          }
        }
      }
    }
  }
  for (int i=0, size=added.size(); i < size; i++) {
    int key=added.keyAt(i);
    Card card=added.get(key);
    if (card != null) {
      try {
        card.added();
      }
 catch (      Exception e) {
        if (card.serviceManager != null) {
          CellSupport cellSupport=card.serviceManager.getService(CellSupport.class);
          if (card.extras != null) {
            cellSupport.onException(card.extras.toString(),e);
          }
 else {
            cellSupport.onException(card.stringType,e);
          }
        }
      }
    }
  }
}
