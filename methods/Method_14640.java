public void onFailedProcess(Process p,Exception exception){
  List<Exception> exceptions=new LinkedList<Exception>();
  exceptions.add(exception);
  onFailedProcess(p,exceptions);
}
