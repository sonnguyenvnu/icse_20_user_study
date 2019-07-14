private void initValidConnectionChecker(){
  if (this.validConnectionChecker != null) {
    return;
  }
  this.validConnectionChecker=new MySqlValidConnectionChecker();
}
