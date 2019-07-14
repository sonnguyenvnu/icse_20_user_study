private void init(int resource,int textViewResourceId){
  mDropDownResource=resource;
  mFieldId=textViewResourceId;
  mHelper=new ThemedSpinnerAdapter.Helper(getContext());
}
