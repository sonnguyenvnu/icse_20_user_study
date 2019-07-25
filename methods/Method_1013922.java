@Override public void dispose(){
  if (scriptEngine != null) {
    scriptEngineManager.removeEngine(engineIdentifier);
  }
}
