private boolean shouldIntercept(final @NonNull Request request){
  return KSUri.isApiUri(Uri.parse(request.url().toString()),this.endpoint);
}
