private static void dump(Collection<GenerationPhase> generationPhases){
  StringBuilder sb=new StringBuilder();
  for (  GenerationPhase gp : generationPhases) {
    sb.append("Phase\n");
    for (    Group g : gp.getGroups()) {
      sb.append('\t');
      sb.append(g);
      sb.append('\n');
    }
  }
  LOG.debug(sb.toString());
}
