private void addColumnToPut(Put p,byte[] cfName,long putTimestamp,Entry e){
  p.addColumn(cfName,e.getColumnAs(StaticBuffer.ARRAY_FACTORY),putTimestamp,e.getValueAs(StaticBuffer.ARRAY_FACTORY));
}
