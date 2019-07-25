/** 
 * ? Map ??????????????? Map
 * @param keys ?
 * @return ? Map
 */
@Override public NutMap omit(String... keys){
  NutMap re=new NutMap();
  for (  Map.Entry<String,Object> en : this.entrySet()) {
    String key=en.getKey();
    if (!Lang.contains(keys,key)) {
      re.put(key,en.getValue());
    }
  }
  return re;
}
