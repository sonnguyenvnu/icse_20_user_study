@Override public void onRateLimitReached(final Consumer<RateLimitStatusEvent> action){
  rateLimitStatusListeners.add(new RateLimitStatusListener(){
    @Override public void onRateLimitStatus(    RateLimitStatusEvent event){
    }
    @Override public void onRateLimitReached(    RateLimitStatusEvent event){
      action.accept(event);
    }
  }
);
}
