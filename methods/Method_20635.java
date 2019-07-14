public void trackNewsletterToggle(final boolean sendNewsletter){
  if (sendNewsletter) {
    this.client.track("Newsletter Subscribe");
  }
 else {
    this.client.track("Newsletter Unsubscribe");
  }
}
