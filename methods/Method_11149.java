public View find(Integer key){
  if (mTipsMap.containsKey(key)) {
    return mTipsMap.get(key);
  }
  return null;
}
