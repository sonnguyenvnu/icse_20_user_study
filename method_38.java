private void _XXXXX_(Map<String,DownloadErrorPolicy> policies,Map<String,String> settings,Properties request,ArtifactReference artifact,RemoteRepositoryContent content,Path localFile,Exception exception,Map<String,Exception> previousExceptions) throws ProxyDownloadException {
  boolean process=true;
  for (  Entry<String,? extends DownloadErrorPolicy> entry : policies.entrySet()) {
    String key=StringUtils.substringAfterLast(entry.getKey(),"#");
    DownloadErrorPolicy policy=entry.getValue();
    String defaultSetting=policy.getDefaultOption();
    String setting=StringUtils.defaultString(settings.get(key),defaultSetting);
    log.debug("Applying [{}] policy with [{}]",key,setting);
    try {
      process=policy.applyPolicy(setting,request,localFile,exception,previousExceptions);
      if (!process) {
        break;
      }
    }
 catch (    PolicyConfigurationException e) {
      log.error(e.getMessage(),e);
    }
  }
  if (process) {
    if (!previousExceptions.containsKey(content.getId())) {
      throw new ProxyDownloadException("An error occurred in downloading from the remote repository, and the policy is to fail immediately",content.getId(),exception);
    }
  }
 else {
    previousExceptions.remove(content.getId());
  }
  log.warn("Transfer error from repository {} for artifact {} , continuing to next repository. Error message: {}",content.getRepository().getId(),Keys.toKey(artifact),exception.getMessage());
  log.debug("Full stack trace",exception);
}