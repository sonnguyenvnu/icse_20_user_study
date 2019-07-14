void buildDictType(){
  String url="datastructures.html#dictionaries";
  State bt=BaseDict.table;
  bt.insert("__getitem__",newTutUrl(url),newFunc(),METHOD);
  bt.insert("__iter__",newTutUrl(url),newFunc(),METHOD);
  bt.insert("get",newTutUrl(url),newFunc(),METHOD);
  bt.insert("items",newTutUrl(url),newFunc(newList(newTuple(Type.UNKNOWN,Type.UNKNOWN))),METHOD);
  bt.insert("keys",newTutUrl(url),newFunc(BaseList),METHOD);
  bt.insert("values",newTutUrl(url),newFunc(BaseList),METHOD);
  String[] dict_method_unknown={"clear","copy","fromkeys","get","iteritems","iterkeys","itervalues","pop","popitem","setdefault","update"};
  for (  String m : dict_method_unknown) {
    bt.insert(m,newTutUrl(url),newFunc(),METHOD);
  }
  String[] dict_method_num={"has_key"};
  for (  String m : dict_method_num) {
    bt.insert(m,newTutUrl(url),newFunc(Type.INT),METHOD);
  }
}
