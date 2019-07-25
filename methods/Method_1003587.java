@Override public String[] produces(){
  if (mProduces != null)   return mProduces;
  String[] pProduces=mParent.produces();
  String[] cProduces=mChild.produces();
  mProduces=mergeRepeat(pProduces,cProduces,true);
  return mProduces;
}
