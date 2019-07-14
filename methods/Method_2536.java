private int[] findNode(int nodeId){
  int[] ret=new int[2];
  int hashId=hashNode(nodeId) % _table.size();
  for (; ; hashId=(hashId + 1) % _table.size()) {
    if (hashId < 0) {
      hashId+=_table.size();
    }
    int unitId=_table.get(hashId);
    if (unitId == 0) {
      break;
    }
    if (areEqual(nodeId,unitId)) {
      ret[0]=unitId;
      ret[1]=hashId;
      return ret;
    }
  }
  ret[1]=hashId;
  return ret;
}
