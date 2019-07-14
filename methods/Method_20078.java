private void populateFeatureSelector(){
  Spinner featureSpinner=(Spinner)findViewById(R.id.featureSelector);
  List<String> options=new ArrayList<>();
  options.add(CLOUD_LABEL_DETECTION);
  options.add(CLOUD_LANDMARK_DETECTION);
  options.add(CLOUD_TEXT_DETECTION);
  options.add(CLOUD_DOCUMENT_TEXT_DETECTION);
  ArrayAdapter<String> dataAdapter=new ArrayAdapter<>(this,R.layout.spinner_style,options);
  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  featureSpinner.setAdapter(dataAdapter);
  featureSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
    @Override public void onItemSelected(    AdapterView<?> parentView,    View selectedItemView,    int pos,    long id){
      selectedMode=parentView.getItemAtPosition(pos).toString();
      createImageProcessor();
      tryReloadAndDetectInImage();
    }
    @Override public void onNothingSelected(    AdapterView<?> arg0){
    }
  }
);
}
