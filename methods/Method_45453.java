public ShutdownMocoRunnerWatcher createShutdownWatcher(final Runner runner,final Integer shutdownPort,final String shutdownKey){
  return new ShutdownMocoRunnerWatcher(shutdownPort,shutdownKey,new ShutdownListener(){
    @Override public void onShutdown(){
      runner.stop();
    }
  }
);
}
