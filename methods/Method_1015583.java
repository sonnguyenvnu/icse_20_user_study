public boolean eval(){
  return sv.getView() != null && sv.getView().size() > 1 && !fd.isMonitorRunning();
}
