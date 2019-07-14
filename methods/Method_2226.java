private void validateUris(@Nullable Uri uri,@Nullable MultiUri multiUri){
  Preconditions.checkState((uri == null) || (multiUri == null),"Cannot specify both uri and multiUri props!");
}
