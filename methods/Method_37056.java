@Deprecated public int findFirstPositionOfCell(int type){
  List<BaseCell> data=getComponents();
  int targetPosition=-1;
  if (data == null || data.isEmpty()) {
    targetPosition=-1;
  }
 else {
    for (int i=0, size=data.size(); i < size; i++) {
      if (String.valueOf(type).equals(data.get(i).stringType)) {
        targetPosition=i;
        break;
      }
    }
  }
  return targetPosition;
}
