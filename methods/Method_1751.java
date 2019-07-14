private String typeOfBytes(byte[] bytes){
  if (bytes.length >= 2) {
    if (bytes[0] == (byte)0xFF && bytes[1] == (byte)0xD8) {
      return "jpg";
    }
 else     if (bytes[0] == (byte)0x89 && bytes[1] == (byte)0x50) {
      return "png";
    }
 else     if (bytes[0] == (byte)0x52 && bytes[1] == (byte)0x49) {
      return "webp";
    }
 else     if (bytes[0] == (byte)0x47 && bytes[1] == (byte)0x49) {
      return "gif";
    }
  }
  return "undefined";
}
