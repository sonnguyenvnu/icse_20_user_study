/** 
 * ????????
 * @param realWord
 * @param frequency
 * @return
 */
public static Vertex newPlaceInstance(String realWord,int frequency){
  return new Vertex(Predefine.TAG_PLACE,realWord,new CoreDictionary.Attribute(Nature.ns,frequency));
}
