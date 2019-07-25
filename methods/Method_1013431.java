public final void recover(ObjectInputStream ois) throws IOException {
  boolean hasFile=ois.readBoolean();
  this.canRead=ois.readBoolean();
  this.isFull=ois.readBoolean();
  try {
    if (hasFile) {
      this.poolFile=(File)ois.readObject();
    }
    if (this.isFull) {
      for (int i=0; i < this.buf.length; i++) {
        this.buf[i]=(TLCState)ois.readObject();
      }
    }
  }
 catch (  ClassNotFoundException e) {
    Assert.fail(EC.SYSTEM_CHECKPOINT_RECOVERY_CORRUPT,e);
  }
}
