private void loadMovieDetail(){
  HttpClient.Builder.getDouBanService().getMovieDetail(subjectsBean.getId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MovieDetailBean>(){
    @Override public void onError(    Throwable e){
      showError();
    }
    @Override public void onComplete(){
      showContentView();
    }
    @Override public void onSubscribe(    Disposable d){
      addSubscription(d);
    }
    @Override public void onNext(    final MovieDetailBean movieDetailBean){
      bindingHeaderView.tvOneDay.setText(String.format("?????%s",movieDetailBean.getYear()));
      bindingHeaderView.tvOneCity.setText(String.format("????/???%s",StringFormatUtil.formatGenres(movieDetailBean.getCountries())));
      bindingHeaderView.setMovieDetailBean(movieDetailBean);
      bindingContentView.setBean(movieDetailBean);
      bindingContentView.executePendingBindings();
      mMoreUrl=movieDetailBean.getAlt();
      mMovieName=movieDetailBean.getTitle();
      transformData(movieDetailBean);
    }
  }
);
}
