private void writeMessageToStringBuilder(StringBuilder sb,List<DeploymentDescriptor> deploys,String info){
  sb.append("\n").append(info).append("(").append(deploys.size()).append(") >>>>>>>\n");
  for (Iterator<DeploymentDescriptor> i=deploys.iterator(); i.hasNext(); ) {
    DeploymentDescriptor dd=i.next();
    String treeSymbol=SYMBOLIC1;
    if (!i.hasNext()) {
      treeSymbol=SYMBOLIC2;
    }
    sb.append(treeSymbol).append(dd.getName()).append("\n");
  }
}
