@Override public void persist(){
  if (cacheFile.isDirectory()) {
    LOG.severe("Cannot persist the cache, the given path points to a directory.");
    return;
  }
  boolean cacheFileShouldBeCreated=!cacheFile.exists();
  if (cacheFileShouldBeCreated) {
    final File parentFile=cacheFile.getAbsoluteFile().getParentFile();
    if (parentFile != null && !parentFile.exists()) {
      parentFile.mkdirs();
    }
  }
  try (DataOutputStream outputStream=new DataOutputStream(new BufferedOutputStream(Files.newOutputStream(cacheFile.toPath())))){
    outputStream.writeUTF(pmdVersion);
    outputStream.writeLong(rulesetChecksum);
    outputStream.writeLong(auxClassPathChecksum);
    outputStream.writeLong(executionClassPathChecksum);
    for (    final Map.Entry<String,AnalysisResult> resultEntry : updatedResultsCache.entrySet()) {
      final List<RuleViolation> violations=resultEntry.getValue().getViolations();
      outputStream.writeUTF(resultEntry.getKey());
      outputStream.writeLong(resultEntry.getValue().getFileChecksum());
      outputStream.writeInt(violations.size());
      for (      final RuleViolation rv : violations) {
        CachedRuleViolation.storeToStream(outputStream,rv);
      }
    }
    if (cacheFileShouldBeCreated) {
      LOG.info("Analysis cache created");
    }
 else {
      LOG.info("Analysis cache updated");
    }
  }
 catch (  final IOException e) {
    LOG.severe("Could not persist analysis cache to file. " + e.getMessage());
  }
}
