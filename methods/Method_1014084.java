@Override public final Object unmarshal(HierarchicalStreamReader reader,UnmarshallingContext context){
  List<EventOption> eventOptions=null;
  NodeList nodes=(NodeList)context.convertAnother(context,NodeList.class);
  NodeIterator nodeIterator=new NodeIterator(nodes.getList());
  NodeList optionNodes=(NodeList)nodeIterator.next();
  if (optionNodes != null) {
    eventOptions=toListOfEventOptions(optionNodes);
  }
  nodeIterator.assertEndOfType();
  EventDescription eventDescription=new EventDescription(eventOptions);
  return eventDescription;
}
