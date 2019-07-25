public void visit(final Object obj,LinkField lnk){
  if (lnk instanceof ManyManyLinkField) {
    final ManyManyLinkField mm=(ManyManyLinkField)lnk;
    Object value=lnk.getValue(obj);
    final List<Map<String,Object>> list=new ArrayList<Map<String,Object>>(Lang.eleSize(value));
    Lang.each(value,new Each<Object>(){
      public void invoke(      int i,      Object ele,      int length) throws ExitLoop, LoopException {
        list.add(new RelationObjectMap(mm,obj,ele));
      }
    }
);
    if (list.isEmpty())     return;
    Entity<Map<String,Object>> en=holder.makeEntity(mm.getRelationName(),list.get(0));
    Pojo pojo=opt.maker().makeInsert(en);
    pojo.setOperatingObject(list);
    for (    Object p : list)     pojo.addParamsBy(p);
    opt.add(pojo);
  }
}
