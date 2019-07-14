private static Phonenumber.PhoneNumber getParsedNumber(PhoneNumberUtil phoneNumberUtil,String phoneText,String country){
  try {
    final Phonenumber.PhoneNumber phoneNumber=phoneNumberUtil.parse(phoneText,country);
    if (phoneNumberUtil.isValidNumber(phoneNumber)) {
      return phoneNumber;
    }
 else {
      Timber.e("getParsedNumber: not a valid phone number" + " for country " + country);
      return null;
    }
  }
 catch (  final NumberParseException e) {
    Timber.e("getParsedNumber: Not able to parse phone number");
    return null;
  }
}
