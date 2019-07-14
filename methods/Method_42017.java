@OnClick(R.id.ll_white_list) public void onWhiteListClicked(View view){
  startActivity(new Intent(getApplicationContext(),BlackWhiteListActivity.class));
}
