public static @Nullable ImageRequest fromUri(@Nullable Uri uri){
  return (uri == null) ? null : ImageRequestBuilder.newBuilderWithSource(uri).build();
}
