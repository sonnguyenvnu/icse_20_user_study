static void bindAliases(Scope s,List<Alias> aliases,int tag) throws Exception {
  for (  Alias a : aliases) {
    if (a.aname != null) {
      NameBinder.bind(s,a.aname,Indexer.idx.builtins.unknown,tag);
    }
  }
}
