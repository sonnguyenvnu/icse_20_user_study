private PersistableCustomer register(Connection<?> connection){
  PersistableCustomer customer=new PersistableCustomer();
  if (connection != null) {
    UserProfile socialMediaProfile=connection.fetchUserProfile();
    ConnectionKey providerKey=connection.getKey();
    customer.setEmailAddress(socialMediaProfile.getEmail());
    customer.setUserName(socialMediaProfile.getEmail());
    customer.setFirstName(socialMediaProfile.getFirstName());
    customer.setLastName(socialMediaProfile.getLastName());
    customer.setProvider(providerKey.getProviderId());
    Address address=new Address();
    address.setFirstName(socialMediaProfile.getFirstName());
    address.setLastName(socialMediaProfile.getLastName());
    address.setCountry(com.salesmanager.core.business.constants.Constants.DEFAULT_COUNTRY);
    customer.setBilling(address);
  }
  return customer;
}
