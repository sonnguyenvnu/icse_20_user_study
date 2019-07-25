@Override public void save(){
  super.save();
  SModuleReference ref=this.getModuleReference();
  if (isBootstrapSolution(ref))   return;
  if (getDescriptorFile() == null || isReadOnly())   return;
  if (mySolutionDescriptor.getLoadException() != null) {
    return;
  }
  try {
    DescriptorIO<SolutionDescriptor> io=DescriptorIOFacade.getInstance().standardProvider().solutionDescriptorIO();
    io.writeToFile(getModuleDescriptor(),getDescriptorFile());
  }
 catch (  Exception ex) {
    Logger.getLogger(getClass()).error("Save failed",ex);
  }
}
