@Override public TarHelperTask changePath(String path,boolean addGoBackItem,OnAsyncTaskFinished<ArrayList<CompressedObjectParcelable>> onFinish){
  return new TarHelperTask(filePath,path,addGoBackItem,onFinish);
}
