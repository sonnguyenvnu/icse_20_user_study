@Override public void displayCode(OpenLocationCode code){
  lastFullCode=code;
  mCodeTV.setText(code.getCode().substring(4));
  try {
    mLocalityTV.setText(Locality.getNearestLocality(code));
    localityValid=true;
  }
 catch (  Locality.NoLocalityException e) {
    mLocalityTV.setText(resources.getString(R.string.no_locality));
    localityValid=false;
  }
}
