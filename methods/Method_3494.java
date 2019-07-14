/** 
 * ???????????
 * @return
 */
public static Vertex newE(){
  return new Vertex(Predefine.TAG_END," ",new CoreDictionary.Attribute(Nature.end,Predefine.MAX_FREQUENCY / 10),CoreDictionary.getWordID(Predefine.TAG_END));
}
