public final void recover(String fname) throws IOException {
  DataInputStream dis=FileUtil.newDFIS(this.chkptName(fname,"chkpt"));
  this.set.recover(dis);
  dis.close();
}
