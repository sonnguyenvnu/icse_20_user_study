@NonNull public Bundle end(){
  Parcel parcel=Parcel.obtain();
  bundle.writeToParcel(parcel,0);
  int size=parcel.dataSize();
  Logger.e(size);
  if (size > 500000) {
    bundle.clear();
  }
  return get();
}
