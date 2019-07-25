private CountryInfo load(String countryCodeIso3166){
  try {
    List<String> countryInfosFiles=Arrays.asList(assetManager.list(BASEPATH));
    if (countryInfosFiles.contains(countryCodeIso3166 + ".yml")) {
      return loadCountryInfo(countryCodeIso3166);
    }
    return null;
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
}
