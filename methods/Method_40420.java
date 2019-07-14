void buildModuleType(){
  String[] attrs={"__doc__","__file__","__name__","__package__"};
  for (  String m : attrs) {
    BaseModule.getTable().update(m,newTutUrl("modules.html"),BaseStr,ATTRIBUTE);
  }
  BaseModule.getTable().update("__dict__",newLibUrl("stdtypes","modules"),newDict(BaseStr,unknown()),ATTRIBUTE);
}
