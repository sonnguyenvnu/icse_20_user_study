public void visit(Object obj,LinkField lnk){
  if (lnk instanceof ManyManyLinkField) {
    final ManyManyLinkField mm=(ManyManyLinkField)lnk;
    Object value=mm.getValue(obj);
    if (Lang.eleSize(value) == 0)     return;
    final Pojo pojo=opt.maker().makeDelete(mm.getRelationName());
    pojo.append(Pojos.Items.cndColumn(mm.getToColumnName(),mm.getLinkedField(),null));
    Lang.each(value,new Each<Object>(){
      public void invoke(      int i,      Object ele,      int length) throws ExitLoop, LoopException {
        pojo.addParamsBy(mm.getLinkedField().getValue(ele));
      }
    }
);
    opt.add(pojo);
  }
}
