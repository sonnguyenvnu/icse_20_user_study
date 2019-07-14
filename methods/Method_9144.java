public void clearFrames(){
  for (int a=0; a < frames.size(); a++) {
    Bitmap bitmap=frames.get(a);
    if (bitmap != null) {
      bitmap.recycle();
    }
  }
  frames.clear();
  if (currentTask != null) {
    currentTask.cancel(true);
    currentTask=null;
  }
  invalidate();
}
