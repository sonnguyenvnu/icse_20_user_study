private void change(){
switch (mStatus) {
case GONE:
    mLoadingView.setVisibility(GONE);
  mErrorView.setVisibility(GONE);
mTheEndView.setVisibility(GONE);
hideLoading();
break;
case LOADING:
mLoadingView.setVisibility(VISIBLE);
mErrorView.setVisibility(GONE);
mTheEndView.setVisibility(GONE);
showLoading();
break;
case ERROR:
mLoadingView.setVisibility(GONE);
mErrorView.setVisibility(VISIBLE);
mTheEndView.setVisibility(GONE);
hideLoading();
break;
case THE_END:
mLoadingView.setVisibility(GONE);
mErrorView.setVisibility(GONE);
mTheEndView.setVisibility(VISIBLE);
hideLoading();
break;
}
}
