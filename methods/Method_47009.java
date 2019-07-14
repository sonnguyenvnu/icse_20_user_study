@Override public RarHelperTask changePath(String path,boolean addGoBackItem,OnAsyncTaskFinished<ArrayList<CompressedObjectParcelable>> onFinish){
  return new RarHelperTask(filePath,path,addGoBackItem,onFinish);
}
