public void dispose(String scriptIdentifier){
  for (  ScriptExtensionProvider provider : scriptExtensionProviders) {
    provider.unload(scriptIdentifier);
  }
}
