@Override public void onSampleSelected(int index){
  getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out).replace(R.id.container,createFragmentForPosition(index)).addToBackStack(String.valueOf(index)).commit();
}
