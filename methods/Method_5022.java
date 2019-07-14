@Requirements.RequirementFlags private int watchRequirements(Requirements requirements){
  requirementsWatcher=new RequirementsWatcher(context,new RequirementListener(),requirements);
  @Requirements.RequirementFlags int notMetRequirements=requirementsWatcher.start();
  if (notMetRequirements == 0) {
    startDownloads();
  }
 else {
    stopDownloads();
  }
  return notMetRequirements;
}
