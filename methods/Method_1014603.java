public void clear(){
  for (  Register register : registers.values()) {
    register.setValue(0);
  }
}
