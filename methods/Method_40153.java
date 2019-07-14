void buildClassType(){
  State t=BaseClass.table;
  for (  String s : list("__name__","__doc__","__module__")) {
    t.insert(s,new Url(DATAMODEL_URL),Type.STR,ATTRIBUTE);
  }
  t.insert("__dict__",new Url(DATAMODEL_URL),new DictType(Type.STR,Type.UNKNOWN),ATTRIBUTE);
}
