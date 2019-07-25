public List find(Map map){
  List list=list();
  Map<String,Object> temp=map;
  for (  Document doc : children) {
    boolean match=true;
    for (    Map.Entry<String,Object> entry : temp.entrySet()) {
      if (!entry.getValue().equals(doc.attributes().get(entry.getKey()))) {
        match=false;
        break;
      }
    }
    if (match) {
      list.add(doc);
    }
  }
  return list;
}
