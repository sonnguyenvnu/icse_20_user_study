@Override public void pop(){
  final long end=System.nanoTime();
  final long len=end - myStack[top].startTime;
  String name=myStack[top].name;
  Task wasTask=myStack[top].task;
  if (wasTask == null) {
    getTask(top - 1).addLeaf(name,len);
  }
 else {
    wasTask.executionTime=len;
  }
  myStack[top].task=null;
  top--;
}
