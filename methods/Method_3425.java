/** 
 * ????
 * @param wordTags [??]/[??]??
 * @return ??????????????????
 */
public boolean learn(String... wordTags){
  String[] words=new String[wordTags.length];
  String[] tags=new String[wordTags.length];
  for (int i=0; i < wordTags.length; i++) {
    String[] wordTag=wordTags[i].split("//");
    words[i]=wordTag[0];
    tags[i]=wordTag[1];
  }
  return learn(new POSInstance(words,tags,model.featureMap));
}
