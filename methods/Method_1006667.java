@Override public CustomerReview populate(PersistableCustomerReview source,CustomerReview target,MerchantStore store,Language language) throws ConversionException {
  Validate.notNull(customerService,"customerService cannot be null");
  Validate.notNull(languageService,"languageService cannot be null");
  Validate.notNull(source.getRating(),"Rating cannot bot be null");
  try {
    if (target == null) {
      target=new CustomerReview();
    }
    if (source.getDate() == null) {
      String date=DateUtil.formatDate(new Date());
      source.setDate(date);
    }
    target.setReviewDate(DateUtil.getDate(source.getDate()));
    if (source.getId() != null && source.getId().longValue() == 0) {
      source.setId(null);
    }
 else {
      target.setId(source.getId());
    }
    Customer reviewer=customerService.getById(source.getCustomerId());
    Customer reviewed=customerService.getById(source.getReviewedCustomer());
    target.setReviewRating(source.getRating());
    target.setCustomer(reviewer);
    target.setReviewedCustomer(reviewed);
    Language lang=languageService.getByCode(language.getCode());
    if (lang == null) {
      throw new ConversionException("Invalid language code, use iso codes (en, fr ...)");
    }
    CustomerReviewDescription description=new CustomerReviewDescription();
    description.setDescription(source.getDescription());
    description.setLanguage(lang);
    description.setName("-");
    description.setCustomerReview(target);
    Set<CustomerReviewDescription> descriptions=new HashSet<CustomerReviewDescription>();
    descriptions.add(description);
    target.setDescriptions(descriptions);
  }
 catch (  Exception e) {
    throw new ConversionException("Cannot populate CustomerReview",e);
  }
  return target;
}
