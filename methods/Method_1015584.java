public String condition(){
  View view=sv.getView();
  return "Membership is " + (view != null ? view.size() : "n/a") + ", FD.Monitor running=" + fd.isMonitorRunning();
}
