private static AtomicFieldData wrap(AtomicFieldData in){
  return new AtomicFieldData(){
    @Override public void close(){
      in.close();
    }
    @Override public long ramBytesUsed(){
      return in.ramBytesUsed();
    }
    @Override public ScriptDocValues<?> getScriptValues(){
      return new ScriptDocValues.Strings(getBytesValues());
    }
    @Override public SortedBinaryDocValues getBytesValues(){
      SortedBinaryDocValues inValues=in.getBytesValues();
      return new SortedBinaryDocValues(){
        @Override public BytesRef nextValue() throws IOException {
          BytesRef encoded=inValues.nextValue();
          return new BytesRef(Uid.decodeId(Arrays.copyOfRange(encoded.bytes,encoded.offset,encoded.offset + encoded.length)));
        }
        @Override public int docValueCount(){
          final int count=inValues.docValueCount();
          assert count == 1;
          return inValues.docValueCount();
        }
        @Override public boolean advanceExact(        int doc) throws IOException {
          return inValues.advanceExact(doc);
        }
      }
;
    }
  }
;
}
