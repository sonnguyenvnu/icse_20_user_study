@SuppressWarnings("unchecked") private void initialize(){
  countries=(List<Country>)super.loadJson("countriesV2.json",Country.class);
}
