private void logInstalledModules(StringBuilder stringBuilder,List<DeploymentDescriptor> deploys){
  long totalTime=0;
  long realStart=0;
  long realEnd=0;
  stringBuilder.append("\n").append("Spring context initialize success module list").append("(").append(deploys.size()).append(") >>>>>>>");
  StringBuilder sb=new StringBuilder();
  for (Iterator<DeploymentDescriptor> i=deploys.iterator(); i.hasNext(); ) {
    DeploymentDescriptor dd=i.next();
    String outTreeSymbol=SYMBOLIC1;
    String innerTreeSymbol1=SYMBOLIC3;
    String innerTreeSymbol2=SYMBOLIC4;
    if (!i.hasNext()) {
      outTreeSymbol=SYMBOLIC2;
      innerTreeSymbol1=SYMBOLIC5;
      innerTreeSymbol2=SYMBOLIC6;
    }
    sb.append(outTreeSymbol).append(dd.getName()).append(" [").append(dd.getElapsedTime()).append(" ms]\n");
    totalTime+=dd.getElapsedTime();
    for (Iterator<String> j=dd.getInstalledSpringXml().iterator(); j.hasNext(); ) {
      String xmlPath=j.next();
      String innerTreeSymbol=innerTreeSymbol1;
      if (!j.hasNext()) {
        innerTreeSymbol=innerTreeSymbol2;
      }
      sb.append(innerTreeSymbol).append(xmlPath).append("\n");
    }
    if (realStart == 0 || dd.getStartTime() < realStart) {
      realStart=dd.getStartTime();
    }
    if (realEnd == 0 || (dd.getStartTime() + dd.getElapsedTime()) > realEnd) {
      realEnd=dd.getStartTime() + dd.getElapsedTime();
    }
  }
  stringBuilder.append(" [totalTime = ").append(totalTime).append(" ms, realTime = ").append(realEnd - realStart).append(" ms]\n").append(sb);
}
