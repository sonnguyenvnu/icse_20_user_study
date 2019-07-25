public List<Model> find(List ids){
  String in_ids=join(ids,",","'");
  List<Model> list=where("id in (" + in_ids + ")").fetch();
  return list;
}
