private StringBuilder usage(StringBuilder sb,Help help){
  for (  String key : getHelpSectionKeys()) {
    IHelpSectionRenderer renderer=getHelpSectionMap().get(key);
    if (renderer != null) {
      sb.append(renderer.render(help));
    }
  }
  return sb;
}
