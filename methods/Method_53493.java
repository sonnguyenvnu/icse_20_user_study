@Override public int hashCode(){
  int result=super.hashCode();
  result=31 * result + (http != null ? http.hashCode() : 0);
  result=31 * result + lifeCycleListeners.hashCode();
  result=31 * result + (handler != null ? handler.hashCode() : 0);
  result=31 * result + (stallWarningsGetParam != null ? stallWarningsGetParam.hashCode() : 0);
  result=31 * result + (stallWarningsParam != null ? stallWarningsParam.hashCode() : 0);
  result=31 * result + streamListeners.hashCode();
  return result;
}
