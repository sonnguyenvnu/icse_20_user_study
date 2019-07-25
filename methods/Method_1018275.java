/** 
 * Start Ark Container
 * @throws ArkRuntimeException
 * @since 0.1.0
 */
public Object start() throws ArkRuntimeException {
  AssertUtils.assertNotNull(arkServiceContainer,"arkServiceContainer is null !");
  if (started.compareAndSet(false,true)) {
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
      @Override public void run(){
        stop();
      }
    }
));
    prepareArkConfig();
    reInitializeArkLogger();
    arkServiceContainer.start();
    Pipeline pipeline=arkServiceContainer.getService(Pipeline.class);
    pipeline.process(pipelineContext);
    System.out.println("Ark container started in " + (System.currentTimeMillis() - start) + " ms.");
  }
  return this;
}
