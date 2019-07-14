@Override public void onEvent(int event,String path){
  if (event == IN_IGNORED) {
    wasStopped=true;
    return;
  }
  long deltaTime=Calendar.getInstance().getTimeInMillis() - lastMessagedTime;
switch (event) {
case CREATE:
case MOVED_TO:
    pathsAdded.add(path);
  break;
case DELETE:
case MOVED_FROM:
pathsRemoved.add(path);
break;
case DELETE_SELF:
case MOVE_SELF:
handler.obtainMessage(GOBACK).sendToTarget();
return;
}
if (deltaTime <= DEFER_CONSTANT) {
new Timer().schedule(new TimerTask(){
@Override public void run(){
if (messagingScheduled) return;
sendMessages();
}
}
,DEFER_CONSTANT - deltaTime);
messagingScheduled=true;
}
 else {
if (messagingScheduled) return;
sendMessages();
}
}
