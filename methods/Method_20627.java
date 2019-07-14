public void trackLoginRegisterTout(final @NonNull LoginReason loginReason){
  this.client.track("Application Login or Signup",new HashMap<String,Object>(){
{
      put("intent",loginReason.trackingString());
    }
  }
);
}
