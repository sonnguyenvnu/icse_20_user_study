private void keepMorePath(final @NonNull Envelope envelope){
  try {
    final URL url=new URL(this.envelopeToMoreUrl.call(envelope));
    this._morePath.onNext(pathAndQueryFromURL(url));
  }
 catch (  MalformedURLException ignored) {
  }
}
