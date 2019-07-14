/** 
 * @return a key that is local to the component's parent. 
 */
String getKey(){
  if (mKey == null && !mHasManualKey) {
    mKey=Integer.toString(getTypeId());
  }
  return mKey;
}
