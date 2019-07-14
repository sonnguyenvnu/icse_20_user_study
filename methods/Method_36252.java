private <T>ArrayAdapter getArrayAdapter(final List<T> list){
  ArrayAdapter<T> spinnerAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list);
  spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
  return spinnerAdapter;
}
