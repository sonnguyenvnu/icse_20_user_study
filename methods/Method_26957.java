private void letsExplodeIt(View clickedView){
  final Rect viewRect=new Rect();
  clickedView.getGlobalVisibleRect(viewRect);
  final Explode explode=new Explode();
  explode.setEpicenterCallback(new Transition.EpicenterCallback(){
    @Override public Rect onGetEpicenter(    Transition transition){
      return viewRect;
    }
  }
);
  explode.excludeTarget(clickedView,true);
  TransitionSet set=new TransitionSet().addTransition(explode).addTransition(new Fade().addTarget(clickedView)).addListener(new TransitionListenerAdapter(){
    @Override public void onTransitionEnd(    Transition transition){
      transition.removeListener(this);
      getActivity().onBackPressed();
    }
  }
);
  TransitionManager.beginDelayedTransition(mRecyclerView,set);
  mRecyclerView.setAdapter(null);
}
