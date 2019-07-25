public static ETag from(Optional<String> value){
  return value.map(ETag::new).orElse(NO_ETAG);
}
