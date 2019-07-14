public String stop(){
  recorder.delete(recorderConfirmedPos,recorder.length());
  String result=recorder.toString();
  recorder=null;
  return result;
}
