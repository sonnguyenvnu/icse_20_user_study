/** 
 * @return true if successful
 */
public boolean build(){
  GenerationSettingsProvider.getInstance().setGenerationSettings(new DefaultModifiableGenerationSettings());
  Iterable<MResource> resources=collectResources(myModelToTargetMap.keySet());
  GenerationPathsController pathsController=new GenerationPathsController(myProject,myContext,resources);
  pathsController.init(myModelToTargetMap.values());
  BuildMakeService buildMakeService=new BuildMakeService();
  MakeSession makeSession=createCleanMakeSession();
  final MakeFacetWrapper makeFacetWrapper=new MakeFacetWrapper(myContext,makeSession,pathsController);
  ReducedMakeFacetConfiguration makeFacetConfiguration=makeFacetWrapper.constructMakeFacetConfiguration();
  IScriptController scriptCtl=makeFacetWrapper.configureFacets();
  try {
    Future<IResult> res=buildMakeService.make(makeSession,resources,null,scriptCtl);
    boolean success=res.get().isSucessful();
    final MPSMakeFilesAfterProcessor afterProcessor=new MPSMakeFilesAfterProcessor(myModelToTargetMap,pathsController,myOutputConsumer,myContext);
    success&=afterProcessor.process(makeFacetConfiguration);
    return success;
  }
 catch (  InterruptedException e) {
    reportError(BUNDLE.getString("error.while.make"),e);
  }
catch (  ExecutionException e) {
    reportError(BUNDLE.getString("error.while.make"),e);
  }
  return false;
}
