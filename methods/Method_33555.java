private void loadMovieDetail(){
  HttpClient.Builder.getDouBanService().getMovieDetail(subjectsBean.getId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MovieDetailBean>(){
    @Override public void onError(    Throwable e){
    }
    @Override public void onComplete(){
    }
    @Override public void onSubscribe(    Disposable d){
    }
    @Override public void onNext(    final MovieDetailBean movieDetailBean){
      binding.include.tvOneDay.setText("?????" + movieDetailBean.getYear());
      binding.include.tvOneCity.setText("????/???" + StringFormatUtil.formatGenres(movieDetailBean.getCountries()));
      binding.include.setMovieDetailBean(movieDetailBean);
      binding.setMovieDetailBean(movieDetailBean);
      mMoreUrl=movieDetailBean.getAlt();
      mMovieName=movieDetailBean.getTitle();
      transformData(movieDetailBean);
    }
  }
);
}
