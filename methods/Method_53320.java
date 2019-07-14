@Override public User updateProfileImage(File image) throws TwitterException {
  checkFileValidity(image);
  return factory.createUser(post(conf.getRestBaseURL() + "account/update_profile_image.json",new HttpParameter[]{new HttpParameter("image",image)}));
}
