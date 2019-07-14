private Optional<String[]> doExtract(final NodeList list){
  List<String> values=newArrayList();
  for (int i=0; i < list.getLength(); i++) {
    Node node=list.item(i);
    values.add(node.getNodeValue());
  }
  return of(values.toArray(new String[values.size()]));
}
