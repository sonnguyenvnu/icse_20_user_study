void buildFunctionType(){
  State t=BaseFunction.table;
  for (  String s : list("func_doc","__doc__","func_name","__name__","__module__")) {
    t.insert(s,new Url(DATAMODEL_URL),Type.STR,ATTRIBUTE);
  }
  t.insert("func_closure",new Url(DATAMODEL_URL),newTuple(),ATTRIBUTE);
  t.insert("func_code",new Url(DATAMODEL_URL),Type.UNKNOWN,ATTRIBUTE);
  t.insert("func_defaults",new Url(DATAMODEL_URL),newTuple(),ATTRIBUTE);
  t.insert("func_globals",new Url(DATAMODEL_URL),new DictType(Type.STR,Type.UNKNOWN),ATTRIBUTE);
  t.insert("func_dict",new Url(DATAMODEL_URL),new DictType(Type.STR,Type.UNKNOWN),ATTRIBUTE);
  for (  String s : list("__func__","im_func")) {
    t.insert(s,new Url(DATAMODEL_URL),new FunType(),METHOD);
  }
}
