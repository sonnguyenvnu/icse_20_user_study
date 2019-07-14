@Override public void onRateLimitStatus(final Consumer<RateLimitStatusEvent> action){
  rateLimitStatusListeners.add(new RateLimitStatusListener(){
    @Override public void onRateLimitStatus(    RateLimitStatusEvent event){
      action.accept(event);
    }
    @Override public void onRateLimitReached(    RateLimitStatusEvent event){
    }
  }
);
}
