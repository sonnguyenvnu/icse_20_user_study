public static boolean verifyBitstreamType(ParsableByteArray data){
  return data.bytesLeft() >= 5 && data.readUnsignedByte() == 0x7F && data.readUnsignedInt() == 0x464C4143;
}
