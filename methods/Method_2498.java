/** 
 * ??????????
 * @param parent   ???
 * @param siblings ???????
 * @return ??????
 */
private int fetch(State parent,List<Map.Entry<Integer,State>> siblings){
  if (parent.isAcceptable()) {
    State fakeNode=new State(-(parent.getDepth() + 1));
    fakeNode.addEmit(parent.getLargestValueId());
    siblings.add(new AbstractMap.SimpleEntry<Integer,State>(0,fakeNode));
  }
  for (  Map.Entry<Character,State> entry : parent.getSuccess().entrySet()) {
    siblings.add(new AbstractMap.SimpleEntry<Integer,State>(entry.getKey() + 1,entry.getValue()));
  }
  return siblings.size();
}
