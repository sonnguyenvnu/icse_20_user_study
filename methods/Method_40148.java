void buildStrType(){
  Type.STR.table.insert("__getslice__",newDataModelUrl("object.__getslice__"),newFunc(Type.STR),METHOD);
  Type.STR.table.insert("__getitem__",newDataModelUrl("object.__getitem__"),newFunc(Type.STR),METHOD);
  Type.STR.table.insert("__iter__",newDataModelUrl("object.__iter__"),newFunc(Type.STR),METHOD);
  String[] str_methods_str={"capitalize","center","decode","encode","expandtabs","format","index","join","ljust","lower","lstrip","partition","replace","rfind","rindex","rjust","rpartition","rsplit","rstrip","strip","swapcase","title","translate","upper","zfill"};
  for (  String m : str_methods_str) {
    Type.STR.table.insert(m,newLibUrl("stdtypes.html#str." + m),newFunc(Type.STR),METHOD);
  }
  String[] str_methods_num={"count","isalnum","isalpha","isdigit","islower","isspace","istitle","isupper","find","startswith","endswith"};
  for (  String m : str_methods_num) {
    Type.STR.table.insert(m,newLibUrl("stdtypes.html#str." + m),newFunc(Type.INT),METHOD);
  }
  String[] str_methods_list={"split","splitlines"};
  for (  String m : str_methods_list) {
    Type.STR.table.insert(m,newLibUrl("stdtypes.html#str." + m),newFunc(newList(Type.STR)),METHOD);
  }
  Type.STR.table.insert("partition",newLibUrl("stdtypes"),newFunc(newTuple(Type.STR)),METHOD);
}
