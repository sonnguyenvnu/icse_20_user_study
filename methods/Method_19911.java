public void setMobileParameter(String mobileParameter){
  Assert.hasText(mobileParameter,"mobile parameter must not be empty or null");
  this.mobileParameter=mobileParameter;
}
