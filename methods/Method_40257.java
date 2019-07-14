static void bindMethodAttrs(FunType cl,int tag) throws Exception {
  if (cl.getTable().getParent() != null) {
    Type cls=cl.getTable().getParent().getType();
    if (cls != null && cls.isClassType()) {
      addReadOnlyAttr(cl,"im_class",cls,CLASS,tag);
      addReadOnlyAttr(cl,"__class__",cls,CLASS,tag);
      addReadOnlyAttr(cl,"im_self",cls,ATTRIBUTE,tag);
      addReadOnlyAttr(cl,"__self__",cls,ATTRIBUTE,tag);
    }
  }
}
