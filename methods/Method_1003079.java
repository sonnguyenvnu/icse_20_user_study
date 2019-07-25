@Override void add(Database database,Value v){
  if (v == ValueNull.INSTANCE) {
    return;
  }
  envelope=GeometryUtils.union(envelope,((ValueGeometry)v.convertTo(Value.GEOMETRY)).getEnvelopeNoCopy());
}
