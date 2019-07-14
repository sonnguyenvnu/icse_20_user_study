/** 
 * initialize the db file 
 * @param raf
 * @throws IOException 
 */
private void initDbFile(RandomAccessFile raf) throws IOException {
  raf.seek(0L);
  raf.write(new byte[8]);
  raf.write(new byte[dbConfig.getTotalHeaderSize()]);
  headerPool=new LinkedList<HeaderBlock>();
  indexPool=new LinkedList<IndexBlock>();
}
