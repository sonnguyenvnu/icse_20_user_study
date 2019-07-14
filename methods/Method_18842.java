public static TypeSpecDataHolder generate(SpecModel specModel,Set<ClassName> blacklistedInterfaces){
  TypeSpecDataHolder.Builder dataHolder=TypeSpecDataHolder.newBuilder();
  for (  TagModel tagModel : specModel.getTags()) {
    if (!blacklistedInterfaces.contains(tagModel.name)) {
      dataHolder.addSuperInterface(tagModel.name);
    }
  }
  return dataHolder.build();
}
