/** 
 * ????
 * @param siblings ?????????
 * @return ????
 */
private int insert(List<Node> siblings,BitSet used){
  if (error_ < 0)   return 0;
  int begin=0;
  int pos=Math.max(siblings.get(0).code + 1,nextCheckPos) - 1;
  int nonzero_num=0;
  int first=0;
  if (allocSize <= pos)   resize(pos + 1);
  outer:   while (true) {
    pos++;
    if (allocSize <= pos)     resize(pos + 1);
    if (check[pos] != 0) {
      nonzero_num++;
      continue;
    }
 else     if (first == 0) {
      nextCheckPos=pos;
      first=1;
    }
    begin=pos - siblings.get(0).code;
    if (allocSize <= (begin + siblings.get(siblings.size() - 1).code)) {
      resize(begin + siblings.get(siblings.size() - 1).code + Character.MAX_VALUE);
    }
    if (used.get(begin)) {
      continue;
    }
    for (int i=1; i < siblings.size(); i++)     if (check[begin + siblings.get(i).code] != 0)     continue outer;
    break;
  }
  if (1.0 * nonzero_num / (pos - nextCheckPos + 1) >= 0.95)   nextCheckPos=pos;
  used.set(begin);
  size=(size > begin + siblings.get(siblings.size() - 1).code + 1) ? size : begin + siblings.get(siblings.size() - 1).code + 1;
  for (int i=0; i < siblings.size(); i++) {
    check[begin + siblings.get(i).code]=begin;
  }
  for (int i=0; i < siblings.size(); i++) {
    List<Node> new_siblings=new ArrayList<Node>();
    if (fetch(siblings.get(i),new_siblings) == 0) {
      base[begin + siblings.get(i).code]=(value != null) ? (-value[siblings.get(i).left] - 1) : (-siblings.get(i).left - 1);
      if (value != null && (-value[siblings.get(i).left] - 1) >= 0) {
        error_=-2;
        return 0;
      }
      progress++;
    }
 else {
      int h=insert(new_siblings,used);
      base[begin + siblings.get(i).code]=h;
    }
  }
  return begin;
}
