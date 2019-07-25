@Override public void dispose(){
  PluginId debuggerPlugin=PluginManager.getPluginByClassName("jetbrains.mps.debug.api.BreakpointCreatorsManager");
  if (debuggerPlugin == null) {
    return;
  }
  BreakpointCreatorsManager manager=BreakpointCreatorsManager.getInstance();
  if (manager == null) {
    return;
  }
  for (  BreakpointCreator creator : SetSequence.fromSet(DebugInfoProvider_AppPluginPart.this.myCreators)) {
    manager.removeCreator(creator);
  }
  SetSequence.fromSet(DebugInfoProvider_AppPluginPart.this.myCreators).clear();
}
