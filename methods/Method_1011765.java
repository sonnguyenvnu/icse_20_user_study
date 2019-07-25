public void dispose(){
  myUpdateQueue.dispose();
  myGutter.removeMessages(ourOwner);
}
