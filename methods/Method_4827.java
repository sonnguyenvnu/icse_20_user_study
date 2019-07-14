public void consume(long pesTimeUs,ParsableByteArray userDataPayload){
  if (userDataPayload.bytesLeft() < 9) {
    return;
  }
  int userDataStartCode=userDataPayload.readInt();
  int userDataIdentifier=userDataPayload.readInt();
  int userDataTypeCode=userDataPayload.readUnsignedByte();
  if (userDataStartCode == USER_DATA_START_CODE && userDataIdentifier == CeaUtil.USER_DATA_IDENTIFIER_GA94 && userDataTypeCode == CeaUtil.USER_DATA_TYPE_CODE_MPEG_CC) {
    CeaUtil.consumeCcData(pesTimeUs,userDataPayload,outputs);
  }
}
