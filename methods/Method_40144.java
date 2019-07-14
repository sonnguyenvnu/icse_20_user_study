void buildTupleType(){
  State bt=BaseTuple.table;
  String[] tuple_methods={"__add__","__contains__","__eq__","__ge__","__getnewargs__","__gt__","__iter__","__le__","__len__","__lt__","__mul__","__ne__","__new__","__rmul__","count","index"};
  for (  String m : tuple_methods) {
    bt.insert(m,newLibUrl("stdtypes"),newFunc(),METHOD);
  }
  bt.insert("__getslice__",newDataModelUrl("object.__getslice__"),newFunc(),METHOD);
  bt.insert("__getitem__",newDataModelUrl("object.__getitem__"),newFunc(),METHOD);
  bt.insert("__iter__",newDataModelUrl("object.__iter__"),newFunc(),METHOD);
}
