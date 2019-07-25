public void close(){
  for (  final Set<Long> entry : rankToNodes.values()) {
    this.writer.append("{rank = same; ");
    for (    final Long l : entry) {
      this.writer.append(l + ";");
    }
    this.writer.append("}\n");
  }
  this.writer.append("}\n");
  if (colorize && this.actionToColors.size() > 1) {
    this.writer.append(dotLegend("DotLegend",this.actionToColors.keySet()));
  }
  this.writer.append("}");
  super.close();
}
