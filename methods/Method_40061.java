static void bindMethodAttrs(@NotNull FunType cl){
  if (cl.table.parent != null) {
    Type cls=cl.table.parent.type;
    if (cls != null && cls instanceof ClassType) {
      addReadOnlyAttr(cl,"im_class",cls,CLASS);
      addReadOnlyAttr(cl,"__class__",cls,CLASS);
      addReadOnlyAttr(cl,"im_self",cls,ATTRIBUTE);
      addReadOnlyAttr(cl,"__self__",cls,ATTRIBUTE);
    }
  }
}
