public void trackSignupNewsletterToggle(final boolean sendNewsletters){
  this.client.track(KoalaEvent.SIGNUP_NEWSLETTER_TOGGLE,new HashMap<String,Object>(){
{
      put("send_newsletters",sendNewsletters);
    }
  }
);
}
