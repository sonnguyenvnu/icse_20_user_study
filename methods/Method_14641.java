public void onFailedProcess(Process p,List<Exception> exceptions){
  _latestExceptions=exceptions;
  _processes.remove(p);
}
