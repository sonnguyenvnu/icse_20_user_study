void buildListType(){
  BaseList.getTable().update("__getslice__",newDataModelUrl("object.__getslice__"),newFunc(BaseList),METHOD);
  BaseList.getTable().update("__getitem__",newDataModelUrl("object.__getitem__"),newFunc(BaseList),METHOD);
  BaseList.getTable().update("__iter__",newDataModelUrl("object.__iter__"),newFunc(BaseList),METHOD);
  String[] list_methods_none={"append","extend","index","insert","pop","remove","reverse","sort"};
  for (  String m : list_methods_none) {
    BaseList.getTable().update(m,newLibUrl("stdtypes"),newFunc(None),METHOD);
  }
  String[] list_methods_num={"count"};
  for (  String m : list_methods_num) {
    BaseList.getTable().update(m,newLibUrl("stdtypes"),newFunc(BaseNum),METHOD);
  }
}
