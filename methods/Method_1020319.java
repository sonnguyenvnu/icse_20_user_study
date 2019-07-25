@Override public void disconnect(){
  if (mediaFile.delete()) {
    Log.d(TAG,"Temp file deleted: " + mediaFile.getAbsolutePath());
  }
}
