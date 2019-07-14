public static RecordEntry[] indexMatch(JanusGraphRelation relation,CompositeIndexType index){
  final IndexField[] fields=index.getFieldKeys();
  final RecordEntry[] match=new RecordEntry[fields.length];
  for (int i=0; i < fields.length; i++) {
    final IndexField f=fields[i];
    final Object value=relation.valueOrNull(f.getFieldKey());
    if (value == null)     return null;
    match[i]=new RecordEntry(relation.longId(),value,f.getFieldKey());
  }
  return match;
}
