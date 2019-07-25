@Nullable private static Collection<ServiceTagInterface> create(@NotNull String serviceId,@NotNull XmlTag xmlTag){
  final Collection<ServiceTagInterface> tags=new ArrayList<>();
  for (  XmlTag tag : xmlTag.findSubTags("tag")) {
    String name=tag.getAttributeValue("name");
    if (name == null) {
      continue;
    }
    ServiceTagInterface serviceTagInterface=XmlServiceTag.create(serviceId,tag);
    if (serviceTagInterface == null) {
      continue;
    }
    tags.add(serviceTagInterface);
  }
  return tags;
}
