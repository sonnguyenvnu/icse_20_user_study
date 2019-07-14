@Override protected final void onPostExecute(ArrayList<CompressedObjectParcelable> zipEntries){
  super.onPostExecute(zipEntries);
  onFinish.onAsyncTaskFinished(zipEntries);
}
