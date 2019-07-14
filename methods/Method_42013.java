private void askPassword(){
  Security.authenticateUser(SettingsActivity.this,new Security.AuthCallBack(){
    @Override public void onAuthenticated(){
      startActivity(new Intent(getApplicationContext(),SecurityActivity.class));
    }
    @Override public void onError(){
      Toast.makeText(getApplicationContext(),R.string.wrong_password,Toast.LENGTH_SHORT).show();
    }
  }
);
}
