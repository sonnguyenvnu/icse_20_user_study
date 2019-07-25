@Override public void push(final OutputStream out) throws IOException {
  Runnable r=new Runnable(){
    @Override public void run(){
      try {
        RandomAccessFile rf=new RandomAccessFile(file,"r");
        arc=SevenZip.openInArchive(null,new RandomAccessFileInStream(rf));
        ISimpleInArchive simpleInArchive=arc.getSimpleInterface();
        ISimpleInArchiveItem realItem=null;
        for (        ISimpleInArchiveItem item : simpleInArchive.getArchiveItems()) {
          if (item.getPath().equals(zeName)) {
            realItem=item;
            break;
          }
        }
        if (realItem == null) {
          LOGGER.trace("No such item " + zeName + " found in archive");
          return;
        }
        realItem.extractSlow(new ISequentialOutStream(){
          @Override public int write(          byte[] data) throws SevenZipException {
            try {
              out.write(data);
            }
 catch (            IOException e) {
              LOGGER.debug("Caught exception",e);
              throw new SevenZipException();
            }
            return data.length;
          }
        }
);
      }
 catch (      FileNotFoundException|SevenZipException e) {
        LOGGER.debug("Unpack error. Possibly harmless.",e.getMessage());
      }
 finally {
        try {
          if (in != null) {
            in.close();
          }
          arc.close();
          out.close();
        }
 catch (        IOException e) {
          LOGGER.debug("Caught exception",e);
        }
      }
    }
  }
;
  new Thread(r,"7Zip Extractor").start();
}
