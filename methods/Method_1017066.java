public static Transform<FullQuery,FullQuery> trace(final QueryTrace.Identifier what){
  final QueryTrace.NamedWatch w=QueryTrace.watch(what);
  return r -> FullQuery.create(w.end(r.trace()),r.errors(),r.groups(),r.statistics(),r.limits(),r.dataDensity());
}
