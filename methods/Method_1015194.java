@Override public List<V> slice(long start,long end){
  if (start < 0 || end > size()) {
    throw new IndexOutOfBoundsException();
  }
  int s=(int)start;
  int e=(int)end;
  int pStart=min(prefixLen,s);
  int pEnd=min(prefixLen,e);
  int pLen=pEnd - pStart;
  Object[] pre=null;
  if (pLen > 0) {
    pre=new Object[1 << log2Ceil(pLen)];
    arraycopy(prefix,pIdx(pStart),pre,pre.length - pLen,pLen);
  }
  int sStart=Math.max(0,s - (prefixLen + root.size()));
  int sEnd=Math.max(0,e - (prefixLen + root.size()));
  int sLen=sEnd - sStart;
  Object[] suf=null;
  if (sLen > 0) {
    suf=new Object[1 << log2Ceil(sLen)];
    arraycopy(suffix,sStart,suf,0,sLen);
  }
  return new List<V>(isLinear(),root.slice(Math.max(0,min(root.size(),s - prefixLen)),Math.max(0,min(root.size(),e - prefixLen)),new Object()),pLen,pre,sLen,suf);
}
