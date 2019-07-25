public void close() throws IOException {
  try {
    writeChunk();
    super.close();
    int oleStreamSize=(int)(fileOut.length() + LittleEndianConsts.LONG_SIZE);
    calculateChecksum(fileOut,(int)_pos);
    dir.createDocument(DEFAULT_POIFS_ENTRY,oleStreamSize,new EncryptedPackageWriter());
    createEncryptionInfoEntry(dir,fileOut);
  }
 catch (  GeneralSecurityException e) {
    throw new IOException(e);
  }
}
