private void logFillViewportInserted(int numInserted,int totalSize){
  if (SectionsDebug.ENABLED) {
    Log.d(SectionsDebug.TAG,"(" + hashCode() + ") filled viewport with " + numInserted + " items (holder.size() = " + totalSize + ")");
  }
}
