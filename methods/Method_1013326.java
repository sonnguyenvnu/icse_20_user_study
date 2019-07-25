public final void recover(String fname) throws IOException {
  BufferedDataInputStream dis=new BufferedDataInputStream(this.chkptName(fname,"chkpt"));
  try {
    while (!dis.atEOF()) {
      Assert.check(!this.put(dis.readLong()),EC.TLC_FP_NOT_IN_SET);
    }
  }
 catch (  EOFException e) {
    throw new IOException("MemFPSet2.recover: failed.");
  }
  dis.close();
}
