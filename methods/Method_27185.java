public static boolean isValidBundleSize(@NonNull Bundle bundle){
  Parcel parcel=Parcel.obtain();
  bundle.writeToParcel(parcel,0);
  return parcel.dataSize() < 500000;
}
