@Override protected Set<Entry> entries(){
  return ImmutableSet.of(new Entry("tbinary",create("x-thrift","TBINARY"),create("vnd.apache.thrift.binary")),new Entry("tcompact",create("x-thrift","TCOMPACT"),create("vnd.apache.thrift.compact")),new Entry("tjson",create("x-thrift","TJSON"),create("x-thrift","TJSON").withCharset(UTF_8),create("vnd.apache.thrift.json"),create("vnd.apache.thrift.json").withCharset(UTF_8)),new Entry("ttext",create("x-thrift","TTEXT"),create("x-thrift","TTEXT").withCharset(UTF_8),create("vnd.apache.thrift.text"),create("vnd.apache.thrift.text").withCharset(UTF_8)));
}
