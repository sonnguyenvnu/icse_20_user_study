@Override public boolean onCallApi(int page,@Nullable String parameter){
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  setCurrentPage(page);
  if (page > lastPage || lastPage == 0 || parameter == null) {
    sendToView(SearchCodeMvp.View::hideProgress);
    return false;
  }
  makeRestCall(RestProvider.getSearchService(isEnterprise()).searchCode(parameter,page),response -> {
    lastPage=response.getLast();
    sendToView(view -> {
      view.onNotifyAdapter(response.isIncompleteResults() ? null : response.getItems(),page);
      if (!response.isIncompleteResults()) {
        view.onSetTabCount(response.getTotalCount());
      }
 else {
        view.onSetTabCount(0);
        view.showMessage(R.string.error,R.string.search_results_warning);
      }
    }
);
  }
);
  return true;
}
