@Override public int findLastPositionOfCell(String type){
  List<BaseCell> data=getComponents();
  int targetPosition=-1;
  if (type == null || data == null || data.isEmpty()) {
    targetPosition=-1;
  }
 else {
    for (int i=data.size() - 1; i >= 0; i--) {
      if (type.equals(data.get(i).stringType)) {
        targetPosition=i;
        break;
      }
    }
  }
  return targetPosition;
}
