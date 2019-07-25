/** 
 * ?? XML ?????????????????
 * @param ele XML ??
 * @param regex ?????????
 * @return ????????
 */
public static List<Element> children(Element ele,String regex){
  final List<Element> list=new ArrayList<Element>(ele.getChildNodes().getLength());
  eachChildren(ele,regex,new Each<Element>(){
    public void invoke(    int index,    Element cld,    int length){
      list.add(cld);
    }
  }
);
  return list;
}
