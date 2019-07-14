public ArrayList countriesForCallingCode(String callingCode){
  if (callingCode.startsWith("+")) {
    callingCode=callingCode.substring(1);
  }
  return callingCodeCountries.get(callingCode);
}
