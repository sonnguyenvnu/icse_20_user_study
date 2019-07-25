@Override public void dispose(MPSProject project){
  ExtensionPoint<BeforeRunTaskProvider<BeforeRunTask>> beforeTasksExtensionPoint=Extensions.getArea(project.getProject()).getExtensionPoint(BeforeRunTaskProvider.EXTENSION_POINT_NAME);
  for (  BeforeRunTaskProvider beforeTask : ListSequence.fromList(BeforeTasksInitializer_ProjectPluginPart.this.myRegisteredBeforeTasks)) {
    beforeTasksExtensionPoint.unregisterExtension(beforeTask);
  }
  ListSequence.fromList(BeforeTasksInitializer_ProjectPluginPart.this.myRegisteredBeforeTasks).clear();
}
