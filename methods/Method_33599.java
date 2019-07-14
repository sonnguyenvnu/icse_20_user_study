private void loadMovieDetail(){
  HttpClient.Builder.getMtimeTicketServer().getFilmDetail(filmItemBean.getId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<FilmDetailBean>(){
    @Override public void onError(    Throwable e){
      showError();
    }
    @Override public void onComplete(){
      showContentView();
    }
    @Override public void onSubscribe(    Disposable d){
      addSubscription(d);
    }
    @Override public void onNext(    final FilmDetailBean bean){
      if (bean != null && bean.getData() != null) {
        if (bean.getData().getBasic() != null) {
          FilmDetailBean.FilmDetailDataBean.BasicBean basic=bean.getData().getBasic();
          bindingHeaderView.tvOneRatingRate.setText(String.format("???%s",basic.getOverallRating()));
          bindingHeaderView.tvOneRatingNumber.setText(String.format("%s???",basic.getPersonCount()));
          bindingHeaderView.tvOneDate.setText(String.format("?????%s %s",basic.getReleaseDate(),basic.getReleaseArea()));
          bindingHeaderView.tvOneTime.setText(String.format("???%s",basic.getMins()));
          bindingContentView.setBean(basic);
          transformData(bean);
        }
        if (bean.getData().getBoxOffice() != null && !TextUtils.isEmpty(bean.getData().getBoxOffice().getTodayBoxDes()) && !TextUtils.isEmpty(bean.getData().getBoxOffice().getTotalBoxDes())) {
          isShowBoxOffice.set(true);
          bindingContentView.setBoxOffice(bean.getData().getBoxOffice());
        }
 else {
          isShowBoxOffice.set(false);
        }
        if (bean.getData().getRelated() != null && !TextUtils.isEmpty(bean.getData().getRelated().getRelatedUrl())) {
          mMoreUrl=bean.getData().getRelated().getRelatedUrl();
          mMoreTitle="???...";
        }
        bindingContentView.executePendingBindings();
      }
    }
  }
);
}
