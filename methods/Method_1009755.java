private void initialize(@NonNull INumberPadTimePicker.State savedInstanceState){
  insertDigits(savedInstanceState.getDigits());
  mAmPmState=savedInstanceState.getAmPmState();
}
