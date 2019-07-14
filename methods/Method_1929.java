private void setupBackendSelector(){
  mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
    @Override public void onItemSelected(    AdapterView<?> parent,    View view,    int position,    long id){
      updateAnimationBackend(mArrayAdapter.getItem(position).createBackend());
    }
    @Override public void onNothingSelected(    AdapterView<?> adapterView){
    }
  }
);
}
