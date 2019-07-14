@OnClick(R.id.ll_n_columns) public void onChangeColumnsClicked(View view){
  new GeneralSetting(SettingsActivity.this).editNumberOfColumns();
}
