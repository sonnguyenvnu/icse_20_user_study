public static Rule compose(AndroidTarget target,String aidlDir,String manifestRule){
  return new GenAidlRule().aidlFilePath(aidlDir).importPath(target.getPath() + "/" + aidlDir).manifest(manifestRule).aidlDeps(targets(target.getMain().getTargetDeps())).name(aidl(target,aidlDir)).ruleType(RuleType.AIDL.getBuckName());
}
