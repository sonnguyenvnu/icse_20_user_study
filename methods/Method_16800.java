public State capture(String[] list){
  MultiState state=new MultiState(true);
  for (  String source : list) {
    state.addState(captureRemoteData(source));
  }
  return state;
}
