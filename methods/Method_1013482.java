@Override public final void put(IModelValue m1,IModelValue m2){
  ModelValue k=(ModelValue)m1;
  ModelValue elem=(ModelValue)m2;
  if (!k.equals(elem) && this.elems[k.index] == null) {
    this.elems[k.index]=elem;
    this.count++;
  }
}
