/** 
 * ??????????????
 * @param messages     ?????
 * @param inAnimResId  ?????resID
 * @param outAnimResID ?????resID
 */
public void startWithList(List<T> messages,@AnimRes int inAnimResId,@AnimRes int outAnimResID){
  if (Utils.isEmpty(messages))   return;
  setMessages(messages);
  postStart(inAnimResId,outAnimResID);
}
