@Override public void init(MPSProject project){
  ExtensionPoint<BeforeRunTaskProvider<BeforeRunTask>> beforeTasksExtensionPoint=Extensions.getArea(project.getProject()).getExtensionPoint(BeforeRunTaskProvider.EXTENSION_POINT_NAME);
{
    BeforeRunTaskProvider beforeTask=(BeforeRunTaskProvider)new AssemblePluginsBeforeTask_BeforeTask();
    ListSequence.fromList(BeforeTasksInitializer_ProjectPluginPart.this.myRegisteredBeforeTasks).addElement(beforeTask);
    beforeTasksExtensionPoint.registerExtension(beforeTask);
  }
{
    BeforeRunTaskProvider beforeTask=(BeforeRunTaskProvider)new MakeDeployScripts_BeforeTask();
    ListSequence.fromList(BeforeTasksInitializer_ProjectPluginPart.this.myRegisteredBeforeTasks).addElement(beforeTask);
    beforeTasksExtensionPoint.registerExtension(beforeTask);
  }
{
    BeforeRunTaskProvider beforeTask=(BeforeRunTaskProvider)new ClearSettingsDirectoryBeforeRunTask_BeforeTask();
    ListSequence.fromList(BeforeTasksInitializer_ProjectPluginPart.this.myRegisteredBeforeTasks).addElement(beforeTask);
    beforeTasksExtensionPoint.registerExtension(beforeTask);
  }
}
