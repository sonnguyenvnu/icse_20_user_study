public static void writeCharSequence(@NonNull Parcel out,@Nullable CharSequence value){
  TextUtils.writeToParcel(value,out,0);
}
