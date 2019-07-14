public void onMediaDrmEvent(int what){
switch (what) {
case ExoMediaDrm.EVENT_KEY_REQUIRED:
    onKeysRequired();
  break;
default :
break;
}
}
