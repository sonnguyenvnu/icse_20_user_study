public SNode resolve(SLanguage language){
  ModuleId langModuleId=ModuleId.regular(MetaIdHelper.getLanguage(language).getIdValue());
  return SNodeOperations.as(resolve(language.getQualifiedName(),langModuleId.toString()),MetaAdapterFactory.getConcept(0xcf935df46994e9cL,0xa132fa109541cba3L,0x2c446791464290f8L,"jetbrains.mps.build.mps.structure.BuildMps_Language"));
}
