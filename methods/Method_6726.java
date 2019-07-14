private static void updatePhotoSizeLocations(ArrayList<TLRPC.PhotoSize> o,ArrayList<TLRPC.PhotoSize> n){
  for (int a=0, N=o.size(); a < N; a++) {
    TLRPC.PhotoSize photoObject=o.get(a);
    for (int b=0, N2=n.size(); b < N2; b++) {
      TLRPC.PhotoSize size=n.get(b);
      if (size instanceof TLRPC.TL_photoSizeEmpty || size instanceof TLRPC.TL_photoCachedSize) {
        continue;
      }
      if (size.type.equals(photoObject.type)) {
        photoObject.location=size.location;
        break;
      }
    }
  }
}
