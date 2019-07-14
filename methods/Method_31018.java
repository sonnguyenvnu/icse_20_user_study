public static CharSequence readCharSequence(@NonNull Parcel in){
  return TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
}
