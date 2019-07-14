void onModelRowClick(CityModel model){
  String imageTransitionName=getResources().getString(R.string.transition_tag_image_named,model.title);
  String titleTransitionName=getResources().getString(R.string.transition_tag_title_named,model.title);
  List<String> names=new ArrayList<>();
  names.add(imageTransitionName);
  names.add(titleTransitionName);
  getRouter().pushController(RouterTransaction.with(new CityDetailController(model.drawableRes,model.title)).pushChangeHandler(new TransitionChangeHandlerCompat(new CityGridSharedElementTransitionChangeHandler(names),new FadeChangeHandler())).popChangeHandler(new TransitionChangeHandlerCompat(new CityGridSharedElementTransitionChangeHandler(names),new FadeChangeHandler())));
}
