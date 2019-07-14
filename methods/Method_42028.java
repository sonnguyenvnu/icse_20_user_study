private void updatePageTitle(int position){
  getSupportActionBar().setTitle(getString(R.string.of,position + 1,adapter.getCount()));
}
