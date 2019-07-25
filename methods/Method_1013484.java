@Override public final IMVPerm compose(IMVPerm perm){
  MVPerm res=new MVPerm();
  for (int i=0; i < this.elems.length; i++) {
    ModelValue mv=this.elems[i];
    if (mv == null) {
      res.put(i,((MVPerm)perm).elems[i]);
    }
 else {
      ModelValue mv1=((MVPerm)perm).elems[mv.index];
      if (mv1 == null) {
        res.put(i,mv);
      }
 else       if (!ModelValue.mvs[i].equals(mv1)) {
        res.put(i,mv1);
      }
    }
  }
  return res;
}
