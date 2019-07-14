private void logFailedModules(StringBuilder stringBuilder,List<DeploymentDescriptor> deploys){
  stringBuilder.append("\n").append("Spring context initialize failed module list").append("(").append(deploys.size()).append(") >>>>>>>\n");
  for (Iterator<DeploymentDescriptor> i=deploys.iterator(); i.hasNext(); ) {
    DeploymentDescriptor dd=i.next();
    String treeSymbol=SYMBOLIC1;
    if (!i.hasNext()) {
      treeSymbol=SYMBOLIC2;
    }
    stringBuilder.append(treeSymbol).append(dd.getName()).append("\n");
  }
}
