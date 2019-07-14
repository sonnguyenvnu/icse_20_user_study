boolean isChecksumError(){
  if (header.getProtection() == Header.MPEG_PROTECTION_CRC) {
    if (header.getLayer() == Header.MPEG_LAYER_3) {
      CRC16 crc16=new CRC16();
      crc16.update(bytes[2]);
      crc16.update(bytes[3]);
      int sideInfoSize=header.getSideInfoSize();
      for (int i=0; i < sideInfoSize; i++) {
        crc16.update(bytes[6 + i]);
      }
      int crc=((bytes[4] & 0xFF) << 8) | (bytes[5] & 0xFF);
      return crc != crc16.getValue();
    }
  }
  return false;
}
