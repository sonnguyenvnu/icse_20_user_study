@Override public void cipher(ByteBuf in,ByteBuf out) throws GeneralSecurityException {
  in.memoryAddress();
  out.memoryAddress();
  Preconditions.checkState(ctx != 0,"Invalid pointer to AES key!");
  int length=in.readableBytes();
  if (length <= 0) {
    return;
  }
  out.ensureWritable(length);
  nativeCipher.cipher(ctx,in.memoryAddress() + in.readerIndex(),out.memoryAddress() + out.writerIndex(),length);
  in.readerIndex(in.writerIndex());
  out.writerIndex(out.writerIndex() + length);
}
