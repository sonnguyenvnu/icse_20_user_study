private void scheduleNameReset(@NonNull final ViewGroup container,@NonNull final List<View> toSharedElements){
  OneShotPreDrawListener.add(true,container,new Runnable(){
    @Override public void run(){
      final int numSharedElements=toSharedElements.size();
      for (int i=0; i < numSharedElements; i++) {
        final View view=toSharedElements.get(i);
        final String name=view.getTransitionName();
        final String inName=sharedElementNames.get(name);
        view.setTransitionName(inName);
      }
    }
  }
);
}
