@Override public LookupSwitchInsnNode parse(String text){
  if (matcher.run(text)) {
    String mapping=matcher.get("MAPPING");
    String dflt=matcher.get("DEFAULT");
    String[] mappingSplit=mapping.split("[,\\s]+");
    if (mappingSplit.length == 0)     fail(text,"Failed to parse mappings");
    String[] labels=new String[mappingSplit.length];
    int[] keys=new int[mappingSplit.length];
    int i=0;
    for (    String map : mappingSplit) {
      String[] mapSplit=map.split("=");
      keys[i]=Integer.parseInt(mapSplit[0]);
      labels[i]=mapSplit[1];
      i++;
    }
    return new NamedLookupSwitchInsnNode(dflt,labels,keys);
  }
  return fail(text,"Expected: mapping[<MAPPING>...] default[<OFFSET/LABEL>]");
}
