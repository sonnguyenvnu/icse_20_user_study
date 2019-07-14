@Override protected CollectorPipeline getCollectorPipeline(){
  return new PageModelCollectorPipeline<T>(pageModelClasses.get(0));
}
