@Override protected void onAttach(@NonNull View view){
  super.onAttach(view);
  Log.i(TAG,"onAttach() called");
  (((ActionBarProvider)getActivity()).getSupportActionBar()).setTitle("Autodispose Demo");
  Observable.interval(1,TimeUnit.SECONDS).doOnDispose(new Action(){
    @Override public void run(){
      Log.i(TAG,"Disposing from onAttach()");
    }
  }
).as(AutoDispose.<Long>autoDisposable((scopeProvider))).subscribe(new Consumer<Long>(){
    @Override public void accept(    Long num){
      Log.i(TAG,"Started in onAttach(), running until onDetach(): " + num);
    }
  }
);
}
