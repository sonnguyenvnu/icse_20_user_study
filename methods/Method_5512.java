@SuppressWarnings("ByteBufferBackingArray") @Override protected void decode(SubtitleInputBuffer inputBuffer){
  ccData.reset(inputBuffer.data.array(),inputBuffer.data.limit());
  boolean captionDataProcessed=false;
  while (ccData.bytesLeft() >= packetLength) {
    byte ccHeader=packetLength == 2 ? CC_IMPLICIT_DATA_HEADER : (byte)ccData.readUnsignedByte();
    int ccByte1=ccData.readUnsignedByte();
    int ccByte2=ccData.readUnsignedByte();
    if ((ccHeader & CC_TYPE_FLAG) != 0) {
      continue;
    }
    if ((selectedField == 1 && (ccHeader & CC_FIELD_FLAG) != NTSC_CC_FIELD_1) || (selectedField == 2 && (ccHeader & CC_FIELD_FLAG) != NTSC_CC_FIELD_2)) {
      continue;
    }
    byte ccData1=(byte)(ccByte1 & 0x7F);
    byte ccData2=(byte)(ccByte2 & 0x7F);
    if (ccData1 == 0 && ccData2 == 0) {
      continue;
    }
    boolean repeatedControlPossible=repeatableControlSet;
    repeatableControlSet=false;
    boolean previousCaptionValid=captionValid;
    captionValid=(ccHeader & CC_VALID_FLAG) == CC_VALID_FLAG;
    if (!captionValid) {
      if (previousCaptionValid) {
        resetCueBuilders();
        captionDataProcessed=true;
      }
      continue;
    }
    captionDataProcessed=true;
    if (!ODD_PARITY_BYTE_TABLE[ccByte1] || !ODD_PARITY_BYTE_TABLE[ccByte2]) {
      resetCueBuilders();
      continue;
    }
    if (((ccData1 & 0xF7) == 0x11) && ((ccData2 & 0xF0) == 0x30)) {
      currentCueBuilder.append(getSpecialChar(ccData2));
      continue;
    }
    if (((ccData1 & 0xF6) == 0x12) && (ccData2 & 0xE0) == 0x20) {
      currentCueBuilder.backspace();
      if ((ccData1 & 0x01) == 0x00) {
        currentCueBuilder.append(getExtendedEsFrChar(ccData2));
      }
 else {
        currentCueBuilder.append(getExtendedPtDeChar(ccData2));
      }
      continue;
    }
    if ((ccData1 & 0xE0) == 0x00) {
      handleCtrl(ccData1,ccData2,repeatedControlPossible);
      continue;
    }
    currentCueBuilder.append(getChar(ccData1));
    if ((ccData2 & 0xE0) != 0x00) {
      currentCueBuilder.append(getChar(ccData2));
    }
  }
  if (captionDataProcessed) {
    if (captionMode == CC_MODE_ROLL_UP || captionMode == CC_MODE_PAINT_ON) {
      cues=getDisplayCues();
    }
  }
}
