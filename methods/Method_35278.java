private void onFabClicked(boolean fromFab){
  SpannableString details=new SpannableString("A small, yet full-featured framework that allows building View-based Android applications");
  details.setSpan(new AbsoluteSizeSpan(16,true),0,details.length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
  final String url="https://github.com/bluelinelabs/Conductor";
  SpannableString link=new SpannableString(url);
  link.setSpan(new URLSpan(url){
    @Override public void onClick(    View widget){
      startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url)));
    }
  }
,0,link.length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
  SpannableStringBuilder description=new SpannableStringBuilder();
  description.append(details);
  description.append("\n\n");
  description.append(link);
  ControllerChangeHandler pushHandler=fromFab ? new TransitionChangeHandlerCompat(new FabToDialogTransitionChangeHandler(),new FadeChangeHandler(false)) : new FadeChangeHandler(false);
  ControllerChangeHandler popHandler=fromFab ? new TransitionChangeHandlerCompat(new FabToDialogTransitionChangeHandler(),new FadeChangeHandler()) : new FadeChangeHandler();
  getRouter().pushController(RouterTransaction.with(new DialogController("Conductor",description)).pushChangeHandler(pushHandler).popChangeHandler(popHandler));
}
