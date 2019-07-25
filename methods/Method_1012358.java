public User cover(UserEntity userEntity){
  User user=new User();
  user.setSex(userEntity.isSex());
  user.setAvatar(userEntity.getAvatar());
  user.setTitleWallPaper(userEntity.getTitlePaper());
  user.setCreatedTime(userEntity.getCreatedTime());
  user.setUpdatedAt(userEntity.getUpdatedTime());
  user.setNick(userEntity.getNick());
  user.setObjectId(userEntity.getUid());
  user.setMobilePhoneNumber(userEntity.getPhone());
  user.setEmail(userEntity.getEmail());
  user.setAddress(userEntity.getAddress());
  user.setBirthDay(userEntity.getBirthDay());
  user.setName(userEntity.getName());
  user.setUsername(userEntity.getUserName());
  user.setSchool(userEntity.getSchool());
  user.setCollege(userEntity.getCollege());
  user.setMajor(userEntity.getMajor());
  user.setEducation(userEntity.getEducation());
  user.setYear(userEntity.getYear());
  user.setClassNumber(userEntity.getClassNumber());
  user.setSignature(userEntity.getSignature());
  user.setCreatedTime(userEntity.getCreatedTime());
  user.setUpdatedAt(userEntity.getUpdatedTime());
  return user;
}
