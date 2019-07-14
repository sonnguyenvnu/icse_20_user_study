/** 
 * ????????
 * @param realWord
 * @param frequency
 * @return
 */
public static Vertex newPersonInstance(String realWord,int frequency){
  return new Vertex(Predefine.TAG_PEOPLE,realWord,new CoreDictionary.Attribute(Nature.nr,frequency));
}
