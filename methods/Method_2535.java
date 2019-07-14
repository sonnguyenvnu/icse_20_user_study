private int[] findUnit(int id){
  int[] ret=new int[2];
  int hashId=hashUnit(id) % _table.size();
  for (; ; hashId=(hashId + 1) % _table.size()) {
    if (hashId < 0) {
      hashId+=_table.size();
    }
    int unitId=_table.get(hashId);
    if (unitId == 0) {
      break;
    }
  }
  ret[1]=hashId;
  return ret;
}
