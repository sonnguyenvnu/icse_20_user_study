@Override @Transactional(readOnly=true) public UserEntity selectByUserNameAndPassword(String plainUsername,String plainPassword){
  Assert.hasLength(plainUsername,"???????");
  Assert.hasLength(plainPassword,"??????");
  return Optional.ofNullable(selectByUsername(plainUsername)).filter(user -> encodePassword(plainPassword,user.getSalt()).equals(user.getPassword())).orElse(null);
}
