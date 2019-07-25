private VersionUtil.ParseResult parse(String src,boolean hasmodel){
  VersionUtil.ParseResult res=parseWithoutCheck(src,hasmodel);
  if (hasmodel) {
    SModel.ImportElement elem=myImports.get(getSModelReference(res.modelID));
    if (elem == null || res.version != myImports.get(getSModelReference(res.modelID)).getUsedVersion()) {
      LOG.error("wrong version of " + src + ", model=" + getSModelReference(res.modelID) + ". Possible reason: merge conflict was not resolved.");
    }
  }
  return res;
}
