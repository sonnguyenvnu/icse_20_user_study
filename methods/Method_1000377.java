List<LinkField> visit(Object obj,String regex,LinkVisitor visitor){
  List<LinkField> list=getList(regex);
  if (null != visitor)   for (  LinkField lnk : list)   visitor.visit(obj,lnk);
  return list;
}
