/** 
 * ?????
 * @param key
 */
public void remove(String key){
  BaseNode branch=this;
  char[] chars=key.toCharArray();
  for (int i=0; i < chars.length - 1; ++i) {
    if (branch == null)     return;
    branch=branch.getChild(chars[i]);
  }
  if (branch == null)   return;
  if (branch.addChild(new Node(chars[chars.length - 1],Status.UNDEFINED_0,value))) {
    --size;
  }
}
