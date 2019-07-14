/** 
 * Initializes and starts web application.
 */
public WebApp start(){
  log=LoggerFactory.getLogger(WebApp.class);
  log.debug("Initializing Madvoc WebApp");
  for (  final Map<String,Object> params : paramsList) {
    madvocContainer.defineParams(params);
  }
  for (  final Props props : propsList) {
    madvocContainer.defineParams(props);
  }
  propsList=null;
  registerMadvocComponents();
  madvocComponents.forEach(madvocComponent -> madvocContainer.registerComponent(madvocComponent.type(),madvocComponent.consumer()));
  madvocComponents=null;
  madvocComponentInstances.forEach(madvocContainer::registerComponentInstance);
  madvocComponentInstances=null;
  configureDefaults();
  madvocContainer.fireEvent(Init.class);
  componentConfigs.accept(madvocContainer);
  componentConfigs=null;
  initialized();
  madvocContainer.fireEvent(Start.class);
  if (!madvocRouterConsumers.isEmpty()) {
    final MadvocRouter madvocRouter=MadvocRouter.create();
    madvocContainer.registerComponentInstance(madvocRouter);
    madvocRouterConsumers.accept(madvocRouter);
  }
  madvocRouterConsumers=null;
  started();
  madvocContainer.fireEvent(Ready.class);
  ready();
  return this;
}
