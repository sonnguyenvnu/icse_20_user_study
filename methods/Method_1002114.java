public AuthRequest locale(Locale locale){
  this.locale=locale.toString();
  this.countryCode=locale.getCountry().toLowerCase();
  this.operatorCountryCode=locale.getCountry().toLowerCase();
  return this;
}
