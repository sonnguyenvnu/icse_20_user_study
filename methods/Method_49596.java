private void addToDocument(String store,String docID,Document doc,List<IndexEntry> content,Map<String,Shape> geoFields,KeyInformation.IndexRetriever information){
  Preconditions.checkNotNull(doc);
  for (  final IndexEntry e : content) {
    Preconditions.checkArgument(!e.hasMetaData(),"Lucene index does not support indexing meta data: %s",e);
    if (log.isTraceEnabled())     log.trace("Adding field [{}] on document [{}]",e.field,docID);
    if (doc.getField(e.field) != null)     doc.removeFields(e.field);
    if (e.value instanceof Number) {
      final Field field;
      final Field sortField;
      if (AttributeUtil.isWholeNumber((Number)e.value)) {
        field=new LongPoint(e.field,((Number)e.value).longValue());
        sortField=new NumericDocValuesField(e.field,((Number)e.value).longValue());
      }
 else {
        field=new DoublePoint(e.field,((Number)e.value).doubleValue());
        sortField=new DoubleDocValuesField(e.field,((Number)e.value).doubleValue());
      }
      doc.add(field);
      doc.add(sortField);
    }
 else     if (AttributeUtil.isString(e.value)) {
      final String str=(String)e.value;
      final Mapping mapping=Mapping.getMapping(store,e.field,information);
      final Field field;
      final Field sortField;
switch (mapping) {
case DEFAULT:
case TEXT:
        field=new TextField(e.field,str.toLowerCase(),Field.Store.YES);
      sortField=null;
    break;
case STRING:
  field=new TextField(e.field,str,Field.Store.YES);
sortField=new SortedDocValuesField(e.field,new BytesRef(str));
break;
default :
throw new IllegalArgumentException("Illegal mapping specified: " + mapping);
}
doc.add(field);
if (sortField != null) {
doc.add(sortField);
}
}
 else if (e.value instanceof Geoshape) {
final Shape shape=((Geoshape)e.value).getShape();
geoFields.put(e.field,shape);
doc.add(new StoredField(e.field,GEOID + e.value.toString()));
}
 else if (e.value instanceof Date) {
doc.add(new LongPoint(e.field,(((Date)e.value).getTime())));
doc.add(new NumericDocValuesField(e.field,(((Date)e.value).getTime())));
}
 else if (e.value instanceof Instant) {
doc.add(new LongPoint(e.field,(((Instant)e.value).toEpochMilli())));
doc.add(new NumericDocValuesField(e.field,(((Instant)e.value).toEpochMilli())));
}
 else if (e.value instanceof Boolean) {
doc.add(new IntPoint(e.field,((Boolean)e.value) ? 1 : 0));
}
 else if (e.value instanceof UUID) {
final Field field=new StringField(e.field,e.value.toString(),Field.Store.YES);
doc.add(field);
}
 else {
throw new IllegalArgumentException("Unsupported type: " + e.value);
}
}
for (final Map.Entry<String,Shape> geo : geoFields.entrySet()) {
if (log.isTraceEnabled()) log.trace("Updating geo-indexes for key {}",geo.getKey());
final KeyInformation ki=information.get(store,geo.getKey());
final SpatialStrategy spatialStrategy=getSpatialStrategy(geo.getKey(),ki);
for (final IndexableField f : spatialStrategy.createIndexableFields(geo.getValue())) {
if (doc.getField(f.name()) != null) {
doc.removeFields(f.name());
}
doc.add(f);
if (spatialStrategy instanceof PointVectorStrategy) {
doc.add(new DoubleDocValuesField(f.name(),f.numericValue() == null ? null : f.numericValue().doubleValue()));
}
}
}
}
