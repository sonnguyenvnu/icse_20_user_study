/** 
 * ?????
 * @param deprelTranslatorPath ?????
 * @return
 */
public IDependencyParser setDeprelTranslater(String deprelTranslatorPath){
  deprelTranslater=GlobalObjectPool.get(deprelTranslatorPath);
  if (deprelTranslater != null)   return this;
  IOUtil.LineIterator iterator=new IOUtil.LineIterator(deprelTranslatorPath);
  deprelTranslater=new TreeMap<String,String>();
  while (iterator.hasNext()) {
    String[] args=iterator.next().split("\\s");
    deprelTranslater.put(args[0],args[1]);
  }
  if (deprelTranslater.size() == 0) {
    deprelTranslater=null;
  }
  GlobalObjectPool.put(deprelTranslatorPath,deprelTranslater);
  return this;
}
