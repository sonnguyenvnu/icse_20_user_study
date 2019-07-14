@Nullable @Override public MediaType contentType(){
  String type=mContentResolver.getType(mUri);
  if (TextUtils.isEmpty(type)) {
    return null;
  }
  return MediaType.parse(type);
}
