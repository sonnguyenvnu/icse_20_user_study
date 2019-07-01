private boolean _XXXXX_(final long position) throws IOException {
  while (true) {
    if (null == currentRecord) {
      currentRecord=nextRecordStream(reader);
    }
    if (null == currentRecord) {
      return false;
    }
    long endPos=currentRecord.record.getTransactionId();
    if (endPos < position) {
      currentRecord=nextRecordStream(reader);
      this.pos=endPos;
      continue;
    }
 else     if (endPos == position) {
      this.pos=position;
      this.currentRecord=null;
      return true;
    }
 else {
      this.currentRecord.payloadStream.skip(this.currentRecord.payloadStream.available() - (endPos - position));
      this.pos=position;
      return true;
    }
  }
}