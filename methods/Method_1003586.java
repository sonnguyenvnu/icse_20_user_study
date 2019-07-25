@Override public String[] path(){
  if (mPaths != null)   return mPaths;
  String[] pPaths=mParent.path();
  if (ArrayUtils.isEmpty(pPaths)) {
    pPaths=mParent.value();
  }
  String[] cPaths=mChild.path();
  if (ArrayUtils.isEmpty(cPaths)) {
    cPaths=mChild.value();
  }
  if (ArrayUtils.isNotEmpty(pPaths)) {
    List<String> paths=new ArrayList<>();
    for (    String pPath : pPaths) {
      for (      String cPath : cPaths) {
        String path=pPath + cPath;
        paths.add(path);
      }
    }
    mPaths=paths.toArray(new String[0]);
  }
 else {
    mPaths=cPaths;
  }
  mPaths=mergeRepeat(mPaths,null,false);
  return mPaths;
}
