@SuppressWarnings("unchecked") private void initialize(){
  countries=(List<Country>)super.loadJson("countriesV1.json",Country.class);
}
