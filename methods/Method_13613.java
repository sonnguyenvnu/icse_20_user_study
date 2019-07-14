@PostConstruct public void init(){
  urlBlockHandlerOptional.ifPresent(WebCallbackManager::setUrlBlockHandler);
  urlCleanerOptional.ifPresent(WebCallbackManager::setUrlCleaner);
  requestOriginParserOptional.ifPresent(WebCallbackManager::setRequestOriginParser);
}
