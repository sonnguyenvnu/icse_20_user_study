@Override public void init(MPSProject project){
  ExtensionPoint<BeforeRunTaskProvider<BeforeRunTask>> beforeTasksExtensionPoint=Extensions.getArea(project.getProject()).getExtensionPoint(BeforeRunTaskProvider.EXTENSION_POINT_NAME);
{
    BeforeRunTaskProvider beforeTask=(BeforeRunTaskProvider)new MakeNodePointers_BeforeTask();
    ListSequence.fromList(BeforeTasksInitializer_ProjectPluginPart.this.myRegisteredBeforeTasks).addElement(beforeTask);
    beforeTasksExtensionPoint.registerExtension(beforeTask);
  }
}
