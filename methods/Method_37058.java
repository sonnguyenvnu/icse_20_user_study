@Deprecated public int findLastPositionOfCell(int type){
  List<BaseCell> data=getComponents();
  int targetPosition=-1;
  if (data == null || data.isEmpty()) {
    targetPosition=-1;
  }
 else {
    for (int i=data.size() - 1; i >= 0; i--) {
      if (String.valueOf(type).equals(data.get(i).stringType)) {
        targetPosition=i;
        break;
      }
    }
  }
  return targetPosition;
}
