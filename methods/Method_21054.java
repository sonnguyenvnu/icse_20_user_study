private boolean shouldIntercept(final @NonNull Request request){
  return KSUri.isWebUri(Uri.parse(request.url().toString()),this.endpoint);
}
