@Override public Object generate(Context context,Uri uri,@Nullable Class<?> target){
  return new Intent(Intent.ACTION_VIEW,uri);
}
