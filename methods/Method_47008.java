@Override public CompressedHelperTask changePath(String path,boolean addGoBackItem,OnAsyncTaskFinished<ArrayList<CompressedObjectParcelable>> onFinish){
  return new GzipHelperTask(context,filePath,path,addGoBackItem,onFinish);
}
