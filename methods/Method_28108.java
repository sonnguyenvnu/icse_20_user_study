private void onResponse(int page,@Nullable Pageable<User> response){
  if (response != null) {
    lastPage=response.getLast();
    sendToView(view -> view.onNotifyAdapter(response.getItems(),page));
  }
 else {
    sendToView(BaseMvp.FAView::hideProgress);
  }
}
