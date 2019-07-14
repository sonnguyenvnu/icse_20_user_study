/** 
 * the callbacks are used to add conditions to allow starting the drawer when holding on the side part of the content
 */
public void addInitDrawerCallback(Callback<Void,Boolean> callBack){
  callBacks.add(callBack);
}
