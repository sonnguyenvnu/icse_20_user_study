private KeyEvent translate(KeyEvent origin,int toKeyCode){
  if (toKeyCode == UNDEFINED) {
    Log.d(TAG,"No need to translate: " + origin);
    return origin;
  }
  Log.d(TAG,"Translating from " + origin.getKeyCode() + " to " + toKeyCode);
  return new KeyEvent(origin.getDownTime(),origin.getEventTime(),origin.getAction(),toKeyCode,origin.getRepeatCount(),origin.getMetaState(),origin.getDeviceId(),origin.getScanCode(),origin.getFlags(),origin.getSource());
}
