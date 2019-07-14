/** 
 * Locates the next SYNC value in the buffer, advancing the position to the byte that immediately follows it. If SYNC was not located, the position is advanced to the limit.
 * @param pesBuffer The buffer whose position should be advanced.
 * @return Whether SYNC was found.
 */
private boolean skipToNextSync(ParsableByteArray pesBuffer){
  while (pesBuffer.bytesLeft() > 0) {
    syncBytes<<=8;
    syncBytes|=pesBuffer.readUnsignedByte();
    if (DtsUtil.isSyncWord(syncBytes)) {
      headerScratchBytes.data[0]=(byte)((syncBytes >> 24) & 0xFF);
      headerScratchBytes.data[1]=(byte)((syncBytes >> 16) & 0xFF);
      headerScratchBytes.data[2]=(byte)((syncBytes >> 8) & 0xFF);
      headerScratchBytes.data[3]=(byte)(syncBytes & 0xFF);
      bytesRead=4;
      syncBytes=0;
      return true;
    }
  }
  return false;
}
