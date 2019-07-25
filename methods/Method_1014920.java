@Override public void run(){
  timer=new Timer();
  timer.schedule(new AppendDataTask(this),500,1);
}
