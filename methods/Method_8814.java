private void storeData(ByteBuffer data){
  try {
    final byte[] input=data.array();
    FileOutputStream fos=new FileOutputStream(file);
    final Deflater deflater=new Deflater(Deflater.BEST_SPEED,true);
    deflater.setInput(input,data.arrayOffset(),data.remaining());
    deflater.finish();
    byte[] buf=new byte[1024];
    while (!deflater.finished()) {
      int byteCount=deflater.deflate(buf);
      fos.write(buf,0,byteCount);
    }
    deflater.end();
    fos.close();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
