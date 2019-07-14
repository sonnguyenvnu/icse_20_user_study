public void releaseEncodingHash(short[] buffer){
  if (encodingHash == null || (buffer != null && buffer.length > encodingHash.length)) {
    encodingHash=buffer;
  }
}
