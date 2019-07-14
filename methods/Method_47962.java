@Override public void publishProgress(Task task,int progress){
  task.onProgressUpdate(progress);
}
