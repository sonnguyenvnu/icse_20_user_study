private static Backstack install(Installer installer,@NonNull Activity activity,@NonNull ViewGroup container,@NonNull List<?> initialKeys){
  if (activity == null) {
    throw new IllegalArgumentException("Activity cannot be null!");
  }
  if (container == null) {
    throw new IllegalArgumentException("State changer cannot be null!");
  }
  if (initialKeys == null || initialKeys.isEmpty()) {
    throw new IllegalArgumentException("Initial keys cannot be null!");
  }
  BackstackHost backstackHost=findBackstackHost(activity);
  if (backstackHost == null) {
    backstackHost=new BackstackHost();
    activity.getFragmentManager().beginTransaction().add(backstackHost,"NAVIGATOR_BACKSTACK_HOST").commit();
    activity.getFragmentManager().executePendingTransactions();
  }
  backstackHost.stateChanger=installer.stateChanger;
  backstackHost.keyFilter=installer.keyFilter;
  backstackHost.keyParceler=installer.keyParceler;
  backstackHost.stateClearStrategy=installer.stateClearStrategy;
  backstackHost.scopedServices=installer.scopedServices;
  backstackHost.globalServices=installer.globalServices;
  backstackHost.stateChangeCompletionListeners=installer.stateChangeCompletionListeners;
  backstackHost.shouldPersistContainerChild=installer.shouldPersistContainerChild;
  backstackHost.container=container;
  backstackHost.initialKeys=initialKeys;
  return backstackHost.initialize(installer.isInitializeDeferred);
}
