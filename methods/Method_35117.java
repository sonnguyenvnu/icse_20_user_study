private void setNameOverrides(@NonNull final View container,@NonNull final List<View> toSharedElements){
  OneShotPreDrawListener.add(true,container,new Runnable(){
    @Override public void run(){
      final int numSharedElements=toSharedElements.size();
      for (int i=0; i < numSharedElements; i++) {
        View view=toSharedElements.get(i);
        String name=view.getTransitionName();
        if (name != null) {
          String inName=findKeyForValue(sharedElementNames,name);
          view.setTransitionName(inName);
        }
      }
    }
  }
);
}
