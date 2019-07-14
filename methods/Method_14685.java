@Override protected Object makeKey(Project project,KeyMaker keyMaker,Criterion c,Object o,int index){
  return keyMaker.makeKey(project,(Row)o,index);
}
