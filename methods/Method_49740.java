@Override public boolean isFFmpegCommandRunning(){
  return ffmpegExecuteAsyncTask != null && !ffmpegExecuteAsyncTask.isProcessCompleted();
}
