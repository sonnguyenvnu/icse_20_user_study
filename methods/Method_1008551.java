public final void close(Runnable onCompletion){
  String scrollId=this.scrollId.get();
  if (Strings.hasLength(scrollId)) {
    clearScroll(scrollId,() -> cleanup(onCompletion));
  }
 else {
    cleanup(onCompletion);
  }
}
