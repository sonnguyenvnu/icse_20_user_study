public SNode resolve(SModuleReference moduleRef){
  String targetName=moduleRef.getModuleName();
  return this.resolve(targetName,moduleRef.getModuleId().toString());
}
