/** 
 * ???????????????
 * @param message ???
 */
private void startWithFixedWidth(String message,@AnimRes int inAnimResId,@AnimRes int outAnimResID){
  int messageLength=message.length();
  int width=Utils.px2dip(getContext(),getWidth());
  if (width == 0) {
    throw new RuntimeException("Please set the width of MarqueeView !");
  }
  int limit=width / textSize;
  List list=new ArrayList();
  if (messageLength <= limit) {
    list.add(message);
  }
 else {
    int size=messageLength / limit + (messageLength % limit != 0 ? 1 : 0);
    for (int i=0; i < size; i++) {
      int startIndex=i * limit;
      int endIndex=((i + 1) * limit >= messageLength ? messageLength : (i + 1) * limit);
      list.add(message.substring(startIndex,endIndex));
    }
  }
  if (messages == null) {
    messages=new ArrayList<>();
  }
  messages.clear();
  messages.addAll(list);
  postStart(inAnimResId,outAnimResID);
}
