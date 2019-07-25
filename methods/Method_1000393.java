public void visit(Object obj,LinkField lnk){
  if (lnk instanceof ManyManyLinkField) {
    final ManyManyLinkField mm=(ManyManyLinkField)lnk;
    final Pojo pojo=opt.maker().makeDelete(mm.getRelationName());
    pojo.append(Pojos.Items.cndColumn(mm.getFromColumnName(),mm.getHostField(),mm.getHostField().getValue(obj)));
    opt.add(pojo);
  }
}
