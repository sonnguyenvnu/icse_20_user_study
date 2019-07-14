private String getErrorMessageByApplicationModule(ApplicationRuntimeModel application){
  StringBuilder sbError=new StringBuilder(512);
  if (application.getDeployRegistry().getPendingEntries().size() > 0) {
    sbError.append("\nModules that could not install(Mainly due to module dependency not satisfied.)").append("(").append(application.getDeployRegistry().getPendingEntries().size()).append(") >>>>>>>>\n");
  }
  for (  DependencyTree.Entry<String,DeploymentDescriptor> entry : application.getDeployRegistry().getPendingEntries()) {
    if (application.getAllDeployments().contains(entry.get())) {
      sbError.append("[").append(entry.getKey()).append("]").append(" depends on ").append(entry.getWaitsFor()).append(", but the latter can not be resolved.").append("\n");
    }
  }
  return sbError.toString();
}
