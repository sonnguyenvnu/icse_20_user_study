/** 
 * ????
 * @param key ???
 * @return ???
 */
public Set<Map.Entry<String,V>> prefixSearch(String key){
  Set<Map.Entry<String,V>> entrySet=new TreeSet<Map.Entry<String,V>>();
  StringBuilder sb=new StringBuilder(key.substring(0,key.length() - 1));
  BaseNode branch=this;
  char[] chars=key.toCharArray();
  for (  char aChar : chars) {
    if (branch == null)     return entrySet;
    branch=branch.getChild(aChar);
  }
  if (branch == null)   return entrySet;
  branch.walk(sb,entrySet);
  return entrySet;
}
