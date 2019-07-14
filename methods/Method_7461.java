public void parseDataHeader(){
  int count=value32(0);
  int base=count * 12 + 4;
  int spot=4;
  for (int i=0; i < count; i++) {
    String callingCode=valueString(spot);
    spot+=4;
    String country=valueString(spot);
    spot+=4;
    int offset=value32(spot) + base;
    spot+=4;
    if (country.equals(defaultCountry)) {
      defaultCallingCode=callingCode;
    }
    countryCallingCode.put(country,callingCode);
    callingCodeOffsets.put(callingCode,offset);
    ArrayList<String> countries=callingCodeCountries.get(callingCode);
    if (countries == null) {
      countries=new ArrayList<>();
      callingCodeCountries.put(callingCode,countries);
    }
    countries.add(country);
  }
  if (defaultCallingCode != null) {
    callingCodeInfo(defaultCallingCode);
  }
}
