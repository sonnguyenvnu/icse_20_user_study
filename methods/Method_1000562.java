public static IocObject wrap(Object obj){
  IocObject iobj=new IocObject();
  if (obj != null)   iobj.setType(obj.getClass());
  iobj.setFactory(Iocs.class.getName() + "#self");
  IocValue ival=new IocValue(null,new StaticValue(obj));
  iobj.addArg(ival);
  return iobj;
}
