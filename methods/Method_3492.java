/** 
 * ????????
 * @param realWord ?????????
 * @return ????
 */
public static Vertex newTimeInstance(String realWord){
  return new Vertex(Predefine.TAG_TIME,realWord,new CoreDictionary.Attribute(Nature.t,1000));
}
