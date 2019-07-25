@Override public void validate(){
  assertTrue(name != null,"name cannot be null");
  assertTrue(email != null,"email cannot be null");
  assertTrue(roleName != null,"role name cannot be null");
  assertTrue(isDisplayedToStudent != null,"displayed to student boolean cannot be null");
}
