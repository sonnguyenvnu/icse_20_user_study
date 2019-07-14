private void refreshSpringContextParallel(final DeploymentDescriptor deployment,final ApplicationRuntimeModel application,final ThreadPoolExecutor executor,final CountDownLatch latch,final List<Future> futures){
  futures.add(executor.submit(new Runnable(){
    @Override public void run(){
      String oldName=Thread.currentThread().getName();
      try {
        Thread.currentThread().setName("sofa-module-start-" + deployment.getModuleName());
        Thread.currentThread().setContextClassLoader(deployment.getClassLoader());
        if (deployment.isSpringPowered() && !application.getFailed().contains(deployment)) {
          doRefreshSpringContext(deployment,application);
        }
        DependencyTree.Entry<String,DeploymentDescriptor> entry=application.getDeployRegistry().getEntry(deployment.getModuleName());
        if (entry != null && entry.getDependsOnMe() != null) {
          for (          final DependencyTree.Entry<String,DeploymentDescriptor> child : entry.getDependsOnMe()) {
            child.getDependencies().remove(entry);
            if (child.getDependencies().size() == 0) {
              refreshSpringContextParallel(child.get(),application,executor,latch,futures);
            }
          }
        }
      }
 catch (      Throwable e) {
        SofaLogger.error(e,"Refreshing Spring Application Context of module {0} got an error." + deployment.getName());
        throw new RuntimeException("Refreshing Spring Application Context of module " + deployment.getName() + " got an error.",e);
      }
 finally {
        latch.countDown();
        Thread.currentThread().setName(oldName);
      }
    }
  }
));
}
