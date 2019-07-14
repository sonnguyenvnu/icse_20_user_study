@Override public void consume(ParsableByteArray data,@Flags int flags){
  boolean payloadUnitStartIndicator=(flags & FLAG_PAYLOAD_UNIT_START_INDICATOR) != 0;
  int payloadStartPosition=C.POSITION_UNSET;
  if (payloadUnitStartIndicator) {
    int payloadStartOffset=data.readUnsignedByte();
    payloadStartPosition=data.getPosition() + payloadStartOffset;
  }
  if (waitingForPayloadStart) {
    if (!payloadUnitStartIndicator) {
      return;
    }
    waitingForPayloadStart=false;
    data.setPosition(payloadStartPosition);
    bytesRead=0;
  }
  while (data.bytesLeft() > 0) {
    if (bytesRead < SECTION_HEADER_LENGTH) {
      if (bytesRead == 0) {
        int tableId=data.readUnsignedByte();
        data.setPosition(data.getPosition() - 1);
        if (tableId == 0xFF) {
          waitingForPayloadStart=true;
          return;
        }
      }
      int headerBytesToRead=Math.min(data.bytesLeft(),SECTION_HEADER_LENGTH - bytesRead);
      data.readBytes(sectionData.data,bytesRead,headerBytesToRead);
      bytesRead+=headerBytesToRead;
      if (bytesRead == SECTION_HEADER_LENGTH) {
        sectionData.reset(SECTION_HEADER_LENGTH);
        sectionData.skipBytes(1);
        int secondHeaderByte=sectionData.readUnsignedByte();
        int thirdHeaderByte=sectionData.readUnsignedByte();
        sectionSyntaxIndicator=(secondHeaderByte & 0x80) != 0;
        totalSectionLength=(((secondHeaderByte & 0x0F) << 8) | thirdHeaderByte) + SECTION_HEADER_LENGTH;
        if (sectionData.capacity() < totalSectionLength) {
          byte[] bytes=sectionData.data;
          sectionData.reset(Math.min(MAX_SECTION_LENGTH,Math.max(totalSectionLength,bytes.length * 2)));
          System.arraycopy(bytes,0,sectionData.data,0,SECTION_HEADER_LENGTH);
        }
      }
    }
 else {
      int bodyBytesToRead=Math.min(data.bytesLeft(),totalSectionLength - bytesRead);
      data.readBytes(sectionData.data,bytesRead,bodyBytesToRead);
      bytesRead+=bodyBytesToRead;
      if (bytesRead == totalSectionLength) {
        if (sectionSyntaxIndicator) {
          if (Util.crc(sectionData.data,0,totalSectionLength,0xFFFFFFFF) != 0) {
            waitingForPayloadStart=true;
            return;
          }
          sectionData.reset(totalSectionLength - 4);
        }
 else {
          sectionData.reset(totalSectionLength);
        }
        reader.consume(sectionData);
        bytesRead=0;
      }
    }
  }
}
