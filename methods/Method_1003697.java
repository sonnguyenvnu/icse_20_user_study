public void drain(Consumer<? super Throwable> resume){
  release();
  if (!isUnread()) {
    throw new RequestBodyAlreadyReadException();
  }
  state=State.READING;
  if (earlyClose) {
    discard();
    resume.accept(null);
  }
 else   if (advertisedLength > maxContentLength || receivedLength > maxContentLength) {
    discard();
    resume.accept(tooLargeException(advertisedLength));
  }
 else   if (receivedLast || isContinueExpected()) {
    release();
    state=State.READ;
    resume.accept(null);
  }
 else {
    listener=new Listener(){
      @Override public void onContent(      HttpContent httpContent){
        httpContent.release();
        if ((receivedLength+=httpContent.content().readableBytes()) > maxContentLength) {
          state=State.READ;
          listener=null;
          resume.accept(tooLargeException(RequestBody.this.receivedLength));
        }
 else         if (httpContent instanceof LastHttpContent) {
          state=State.READ;
          listener=null;
          resume.accept(null);
        }
 else {
          ctx.read();
        }
      }
      @Override public void onEarlyClose(){
        resume.accept(null);
      }
    }
;
    ctx.read();
  }
}
