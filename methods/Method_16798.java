public State listFile(int index){
  State state=new MultiState(true);
  state.putInfo("start",index);
  state.putInfo("total",0);
  return state;
}
