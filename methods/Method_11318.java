/** 
 * ????
 * @param time
 */
@SuppressLint("HandlerLeak") public void setTextStillTime(final long time){
  handler=new Handler(){
    @Override public void handleMessage(    Message msg){
switch (msg.what) {
case FLAG_START_AUTO_SCROLL:
        if (textList.size() > 0) {
          currentId++;
          setText(textList.get(currentId % textList.size()));
        }
      handler.sendEmptyMessageDelayed(FLAG_START_AUTO_SCROLL,time);
    break;
case FLAG_STOP_AUTO_SCROLL:
  handler.removeMessages(FLAG_START_AUTO_SCROLL);
break;
default :
break;
}
}
}
;
}
