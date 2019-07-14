@OnItemSelected(R.id.spinner) public void onFrequencySelected(int position){
  if (position < 0 || position > 4)   throw new IllegalArgumentException();
  int freqNums[]={1,1,2,5,3};
  int freqDens[]={1,7,7,7,7};
  setFrequency(new Frequency(freqNums[position],freqDens[position]));
}
