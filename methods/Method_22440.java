public void startTask(String name,int maxValue){
  finished=false;
  progressBar.setString(name);
  progressBar.setIndeterminate(maxValue == UNKNOWN);
  progressBar.setMaximum(maxValue);
}
