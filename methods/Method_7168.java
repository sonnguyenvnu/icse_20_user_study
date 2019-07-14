private TLRPC.PhotoSize getThumbForSecretChat(ArrayList<TLRPC.PhotoSize> arrayList){
  if (arrayList == null || arrayList.isEmpty()) {
    return null;
  }
  for (int a=0, N=arrayList.size(); a < N; a++) {
    TLRPC.PhotoSize size=arrayList.get(a);
    if (size == null || size instanceof TLRPC.TL_photoStrippedSize || size instanceof TLRPC.TL_photoSizeEmpty || size.location == null) {
      continue;
    }
    TLRPC.TL_photoSize photoSize=new TLRPC.TL_photoSize();
    photoSize.type=size.type;
    photoSize.w=size.w;
    photoSize.h=size.h;
    photoSize.size=size.size;
    photoSize.bytes=size.bytes;
    if (photoSize.bytes == null) {
      photoSize.bytes=new byte[0];
    }
    photoSize.location=new TLRPC.TL_fileLocation_layer82();
    photoSize.location.dc_id=size.location.dc_id;
    photoSize.location.volume_id=size.location.volume_id;
    photoSize.location.local_id=size.location.local_id;
    photoSize.location.secret=size.location.secret;
    return photoSize;
  }
  return null;
}
