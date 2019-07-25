@NotNull public static XmlService create(@NotNull String id,@NotNull String className,boolean isPublic){
  XmlService xmlService=new XmlService(id);
  xmlService.className=className;
  xmlService.isPublic=isPublic;
  return xmlService;
}
