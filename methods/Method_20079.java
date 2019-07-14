private void populateSizeSelector(){
  Spinner sizeSpinner=(Spinner)findViewById(R.id.sizeSelector);
  List<String> options=new ArrayList<>();
  options.add(SIZE_PREVIEW);
  options.add(SIZE_1024_768);
  options.add(SIZE_640_480);
  ArrayAdapter<String> dataAdapter=new ArrayAdapter<>(this,R.layout.spinner_style,options);
  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  sizeSpinner.setAdapter(dataAdapter);
  sizeSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
    @Override public void onItemSelected(    AdapterView<?> parentView,    View selectedItemView,    int pos,    long id){
      selectedSize=parentView.getItemAtPosition(pos).toString();
      tryReloadAndDetectInImage();
    }
    @Override public void onNothingSelected(    AdapterView<?> arg0){
    }
  }
);
}
