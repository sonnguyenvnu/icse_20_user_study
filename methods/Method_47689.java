public boolean validate(){
  Resources res=getResources();
  if (getName().isEmpty()) {
    tvName.setError(res.getString(R.string.validation_name_should_not_be_blank));
    return false;
  }
  return true;
}
