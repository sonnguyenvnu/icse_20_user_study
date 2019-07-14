void buildModuleType(){
  String[] attrs={"__doc__","__file__","__name__","__package__"};
  for (  String m : attrs) {
    BaseModule.table.insert(m,newTutUrl("modules.html"),Type.STR,ATTRIBUTE);
  }
  BaseModule.table.insert("__dict__",newLibUrl("stdtypes","modules"),newDict(Type.STR,Type.UNKNOWN),ATTRIBUTE);
}
