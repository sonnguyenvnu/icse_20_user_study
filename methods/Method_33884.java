private PhotoInfo createPhoto(String id,String userId){
  PhotoInfo photo=new PhotoInfo();
  photo.setId(id);
  photo.setName("photo" + id + ".jpg");
  photo.setUserId(userId);
  photo.setResourceURL("/org/springframework/security/oauth/examples/sparklr/impl/resources/" + photo.getName());
  return photo;
}
