/** 
 * start sofa module parallel
 * @param application
 */
private void refreshSpringContextParallel(ApplicationRuntimeModel application){
  ClassLoader oldClassLoader=Thread.currentThread().getContextClassLoader();
  List<DeploymentDescriptor> coreRoots=new ArrayList<>();
  ThreadPoolExecutor executor=new ThreadPoolExecutor(CPU_COUNT + 1,CPU_COUNT + 1,60,TimeUnit.MILLISECONDS,new SynchronousQueue<Runnable>(),new NamedThreadFactory("sofa-module-start"),new ThreadPoolExecutor.CallerRunsPolicy());
  try {
    for (    DeploymentDescriptor deployment : application.getResolvedDeployments()) {
      DependencyTree.Entry entry=application.getDeployRegistry().getEntry(deployment.getModuleName());
      if (entry != null && entry.getDependencies() == null) {
        coreRoots.add(deployment);
      }
    }
    refreshSpringContextParallel(coreRoots,application.getResolvedDeployments().size(),application,executor);
  }
  finally {
    executor.shutdown();
    Thread.currentThread().setContextClassLoader(oldClassLoader);
  }
}
