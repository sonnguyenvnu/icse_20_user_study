public static boolean make(final Project project,final SModel model){
  if (SwingUtilities.isEventDispatchThread()) {
    if (LOG.isEnabledFor(Level.ERROR)) {
      LOG.error("Must be called not from EDT");
    }
    return false;
  }
  IScript scr=new ScriptBuilder().withFacetNames(new IFacet.Name("jetbrains.mps.lang.core.Generate"),new IFacet.Name("jetbrains.mps.lang.core.TextGen"),new IFacet.Name("jetbrains.mps.make.facets.JavaCompile"),new IFacet.Name("jetbrains.mps.make.facets.ReloadClasses"),new IFacet.Name("jetbrains.mps.make.facets.Make")).withFinalTarget(new ITarget.Name("jetbrains.mps.make.facets.Make.make")).toScript();
  final MessagesViewTool mvt=project.getComponent(MessagesViewTool.class);
  IMessageHandler messageHandler=mvt.newHandler("Console Make",true).restrict(MessageKind.ERROR);
  MakeSession session=new MakeSession(project,messageHandler,true);
  IMakeService makeService=project.getComponent(MakeServiceComponent.class).get();
  if (makeService.openNewSession(session)) {
    Future<IResult> future=makeService.make(session,new ModelsToResources(Sequence.<SModel>singleton(model)).canGenerateCondition(new _FunctionTypes._return_P1_E0<Boolean,SModel>(){
      public Boolean invoke(      SModel m){
        return true;
      }
    }
).resources(),scr);
    try {
      return future.get().isSucessful();
    }
 catch (    InterruptedException e) {
      if (LOG.isEnabledFor(Level.ERROR)) {
        LOG.error("Error on making temporary model",e);
      }
    }
catch (    ExecutionException e) {
      if (LOG.isEnabledFor(Level.ERROR)) {
        LOG.error("Error on making temporary model",e);
      }
    }
  }
  return false;
}
