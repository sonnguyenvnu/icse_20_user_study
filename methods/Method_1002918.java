@Override protected Set<Entry> entries(){
  return ImmutableSet.of(new Entry("gproto",create("application","grpc"),create("application","grpc+proto")),new Entry("gjson",create("application","grpc+json")),new Entry("gproto-web",create("application","grpc-web+proto"),create("application","grpc-web")),new Entry("gjson-web",create("application","grpc-web+json")));
}
