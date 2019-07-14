public static MocoEventAction get(final String url){
  return get(text(checkNotNullOrEmpty(url,"URL should not be null")));
}
