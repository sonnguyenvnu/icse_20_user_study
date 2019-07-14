@NonNull @Override protected View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container){
  Log.i(TAG,"onCreateView() called");
  View view=inflater.inflate(R.layout.controller_lifecycle,container,false);
  view.setBackgroundColor(ContextCompat.getColor(container.getContext(),R.color.red_300));
  unbinder=ButterKnife.bind(this,view);
  tvTitle.setText(getResources().getString(R.string.rxlifecycle_title,TAG));
  Observable.interval(1,TimeUnit.SECONDS).doOnUnsubscribe(new Action0(){
    @Override public void call(){
      Log.i(TAG,"Unsubscribing from onCreateView)");
    }
  }
).compose(this.<Long>bindUntilEvent(ControllerEvent.DESTROY_VIEW)).subscribe(new Action1<Long>(){
    @Override public void call(    Long num){
      Log.i(TAG,"Started in onCreateView(), running until onDestroyView(): " + num);
    }
  }
);
  return view;
}
