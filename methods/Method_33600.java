/** 
 * ????????
 */
private void transformData(final FilmDetailBean bean){
  if (bean.getData().getBasic().getActors() != null && bean.getData().getBasic().getActors().size() > 0) {
    isShowActor.set(true);
    FilmDetailBean.ActorsBean director=bean.getData().getBasic().getDirector();
    if (director != null) {
      director.setRoleName("??");
      bean.getData().getBasic().getActors().add(0,director);
    }
    setAdapter(bean.getData().getBasic().getActors());
  }
 else {
    isShowActor.set(false);
  }
  if (bean.getData().getBasic().getVideo() != null && !TextUtils.isEmpty(bean.getData().getBasic().getVideo().getUrl())) {
    isShowVideo.set(true);
    FilmDetailBean.FilmDetailDataBean.BasicBean.VideoBean video=bean.getData().getBasic().getVideo();
    bindingContentView.setVideo(video);
    DensityUtil.formatHeight(bindingContentView.ivVideo,DensityUtil.getDisplayWidth() - DensityUtil.dip2px(40),(640f / 360),3);
    DensityUtil.setViewMargin(bindingContentView.ivVideo,true,20,20,10,10);
    bindingContentView.ivVideo.setOnClickListener(view -> WebViewActivity.loadUrl(this,video.getHightUrl(),video.getTitle(),true));
  }
 else {
    isShowVideo.set(false);
  }
  if (bean.getData().getBasic().getStageImg() != null && bean.getData().getBasic().getStageImg().getList() != null && bean.getData().getBasic().getStageImg().getList().size() > 0) {
    setImageAdapter(bean.getData().getBasic().getStageImg().getList());
  }
}
