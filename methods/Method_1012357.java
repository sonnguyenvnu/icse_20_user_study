public UserEntity cover(User currentUser,boolean isStranger,boolean isBlack,int blackType){
  UserEntity userEntity=new UserEntity();
  userEntity.setIsStranger(isStranger);
  userEntity.setTitlePaper(currentUser.getTitleWallPaper());
  userEntity.setUpdatedTime(currentUser.getUpdatedAt());
  userEntity.setCreatedTime(currentUser.getCreatedAt());
  userEntity.setSex(currentUser.isSex());
  userEntity.setAvatar(currentUser.getAvatar());
  userEntity.setNick(currentUser.getNick());
  userEntity.setUid(currentUser.getObjectId());
  userEntity.setAddress(currentUser.getAddress());
  userEntity.setPhone(currentUser.getMobilePhoneNumber());
  userEntity.setEmail(currentUser.getEmail());
  userEntity.setBirthDay(currentUser.getBirthDay());
  userEntity.setBlack(isBlack);
  userEntity.setBlackType(blackType);
  userEntity.setClassNumber(currentUser.getClassNumber());
  userEntity.setCollege(currentUser.getCollege());
  userEntity.setEducation(currentUser.getEducation());
  userEntity.setMajor(currentUser.getMajor());
  userEntity.setName(currentUser.getName());
  userEntity.setUserName(currentUser.getUsername());
  userEntity.setSchool(currentUser.getSchool());
  userEntity.setSignature(currentUser.getSignature());
  userEntity.setYear(currentUser.getYear());
  return userEntity;
}
