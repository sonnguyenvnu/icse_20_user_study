@OnCheckedChanged({R.id.radio_button_all,R.id.radio_button_folder}) public void onSegmentedChecked(RadioButton radioButton,boolean isChecked){
  int index=segmentedControls.indexOf(radioButton);
  Fragment fragment=mFragments.get(index);
  FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
  if (isChecked) {
    fragmentTransaction.show(fragment);
  }
 else {
    fragmentTransaction.hide(fragment);
  }
  fragmentTransaction.commit();
}
