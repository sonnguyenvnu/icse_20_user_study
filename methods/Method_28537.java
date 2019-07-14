private void onHandleLayoutState(){
  setEmptyText(emptyTextValue);
switch (layoutState) {
case SHOW_PROGRESS_STATE:
    showProgress();
  break;
case HIDE_PROGRESS_STATE:
hideProgress();
break;
case HIDE_RELOAD_STATE:
hideReload();
break;
case SHOW_RELOAD_STATE:
showReload();
break;
case HIDDEN:
setVisibility(GONE);
break;
case SHOW_EMPTY_STATE:
showEmptyState();
break;
case SHOWN:
setVisibility(VISIBLE);
showReload();
break;
}
}
