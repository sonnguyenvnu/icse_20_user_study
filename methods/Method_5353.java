/** 
 * If the segment is fully encrypted, returns an  {@link Aes128DataSource} that wraps the originalin order to decrypt the loaded data. Else returns the original.
 */
private static DataSource buildDataSource(DataSource dataSource,byte[] fullSegmentEncryptionKey,byte[] encryptionIv){
  if (fullSegmentEncryptionKey != null) {
    return new Aes128DataSource(dataSource,fullSegmentEncryptionKey,encryptionIv);
  }
  return dataSource;
}
