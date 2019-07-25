public final void recover(ObjectInputStream ois) throws IOException {
  boolean hasFile=ois.readBoolean();
  if (hasFile) {
    try {
      this.poolFile=(File)ois.readObject();
      for (int i=0; i < this.buf.length; i++) {
        this.buf[i]=(TLCState)ois.readObject();
      }
    }
 catch (    ClassNotFoundException e) {
      Assert.fail(EC.SYSTEM_CHECKPOINT_RECOVERY_CORRUPT,e);
    }
  }
 else {
    this.poolFile=null;
  }
}
