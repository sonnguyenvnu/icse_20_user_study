public void visit(Object obj,LinkField lnk){
  if (lnk instanceof ManyManyLinkField) {
    ManyManyLinkField mm=(ManyManyLinkField)lnk;
    Entity<?> en=opt.makeEntity(mm.getRelationName(),map);
    Pojo pojo=opt.maker().makeUpdate(en,null);
    pojo.setOperatingObject(map);
    pojo.append(items);
    opt.add(pojo);
  }
}
