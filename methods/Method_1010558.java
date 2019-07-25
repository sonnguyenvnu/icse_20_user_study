public Iterable<IResource> resources(){
  Iterable<SModel> smds=Sequence.fromIterable(models).where(myCanGenerateCondition).distinct();
  smds=Sequence.fromIterable(smds).sort(new ISelector<SModel,String>(){
    public String select(    SModel desc){
      return desc.getModule().getModuleName();
    }
  }
,true);
  return arrangeByModule(smds);
}
