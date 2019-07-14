private void dispatchAndUpdateViewHolders(UpdateOp op){
  if (op.cmd == UpdateOp.ADD || op.cmd == UpdateOp.MOVE) {
    throw new IllegalArgumentException("should not dispatch add or move for pre layout");
  }
  if (DEBUG) {
    Log.d(TAG,"dispatch (pre)" + op);
    Log.d(TAG,"postponed state before:");
    for (    UpdateOp updateOp : mPostponedList) {
      Log.d(TAG,updateOp.toString());
    }
    Log.d(TAG,"----");
  }
  int tmpStart=updatePositionWithPostponed(op.positionStart,op.cmd);
  if (DEBUG) {
    Log.d(TAG,"pos:" + op.positionStart + ",updatedPos:" + tmpStart);
  }
  int tmpCnt=1;
  int offsetPositionForPartial=op.positionStart;
  final int positionMultiplier;
switch (op.cmd) {
case UpdateOp.UPDATE:
    positionMultiplier=1;
  break;
case UpdateOp.REMOVE:
positionMultiplier=0;
break;
default :
throw new IllegalArgumentException("op should be remove or update." + op);
}
for (int p=1; p < op.itemCount; p++) {
final int pos=op.positionStart + (positionMultiplier * p);
int updatedPos=updatePositionWithPostponed(pos,op.cmd);
if (DEBUG) {
Log.d(TAG,"pos:" + pos + ",updatedPos:" + updatedPos);
}
boolean continuous=false;
switch (op.cmd) {
case UpdateOp.UPDATE:
continuous=updatedPos == tmpStart + 1;
break;
case UpdateOp.REMOVE:
continuous=updatedPos == tmpStart;
break;
}
if (continuous) {
tmpCnt++;
}
 else {
UpdateOp tmp=obtainUpdateOp(op.cmd,tmpStart,tmpCnt,op.payload);
if (DEBUG) {
Log.d(TAG,"need to dispatch separately " + tmp);
}
dispatchFirstPassAndUpdateViewHolders(tmp,offsetPositionForPartial);
recycleUpdateOp(tmp);
if (op.cmd == UpdateOp.UPDATE) {
offsetPositionForPartial+=tmpCnt;
}
tmpStart=updatedPos;
tmpCnt=1;
}
}
Object payload=op.payload;
recycleUpdateOp(op);
if (tmpCnt > 0) {
UpdateOp tmp=obtainUpdateOp(op.cmd,tmpStart,tmpCnt,payload);
if (DEBUG) {
Log.d(TAG,"dispatching:" + tmp);
}
dispatchFirstPassAndUpdateViewHolders(tmp,offsetPositionForPartial);
recycleUpdateOp(tmp);
}
if (DEBUG) {
Log.d(TAG,"post dispatch");
Log.d(TAG,"postponed state after:");
for (UpdateOp updateOp : mPostponedList) {
Log.d(TAG,updateOp.toString());
}
Log.d(TAG,"----");
}
}
