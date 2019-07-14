@OnClick(R.id.progress_done_cancel_sheet) void cancel(){
  if (done) {
    dismiss();
  }
 else {
    if (disposable != null && !disposable.isDisposed())     disposable.dispose();
    listener.onCompleted();
    dismiss();
  }
}
