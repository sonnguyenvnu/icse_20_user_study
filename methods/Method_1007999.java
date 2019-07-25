private void install(Terminal terminal,boolean isBatch,Path tmpRoot,Environment env) throws Exception {
  List<Path> deleteOnFailure=new ArrayList<>();
  deleteOnFailure.add(tmpRoot);
  try {
    if (MetaPluginInfo.isMetaPlugin(tmpRoot)) {
      installMetaPlugin(terminal,isBatch,tmpRoot,env,deleteOnFailure);
    }
 else {
      installPlugin(terminal,isBatch,tmpRoot,env,deleteOnFailure);
    }
  }
 catch (  Exception installProblem) {
    try {
      IOUtils.rm(deleteOnFailure.toArray(new Path[0]));
    }
 catch (    IOException exceptionWhileRemovingFiles) {
      installProblem.addSuppressed(exceptionWhileRemovingFiles);
    }
    throw installProblem;
  }
}
