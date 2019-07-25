public static Set<Parser> parsers(){
  final Set<Parser> c=new HashSet<Parser>();
  for (  Set<Parser> pl : ext2parser.values())   c.addAll(pl);
  for (  Set<Parser> pl : mime2parser.values())   c.addAll(pl);
  return c;
}
