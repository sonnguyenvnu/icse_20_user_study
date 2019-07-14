public void setData(TLObject object,TLRPC.EncryptedChat ec,CharSequence n,CharSequence s,boolean needCount,boolean saved){
  currentName=n;
  if (object instanceof TLRPC.User) {
    user=(TLRPC.User)object;
    chat=null;
  }
 else   if (object instanceof TLRPC.Chat) {
    chat=(TLRPC.Chat)object;
    user=null;
  }
  encryptedChat=ec;
  subLabel=s;
  drawCount=needCount;
  savedMessages=saved;
  update(0);
}
