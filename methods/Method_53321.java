@Override public User updateProfileImage(InputStream image) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "account/update_profile_image.json",new HttpParameter[]{new HttpParameter("image","image",image)}));
}
