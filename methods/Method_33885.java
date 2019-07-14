public InputStream loadPhoto(String id){
  Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
  if (authentication.getPrincipal() instanceof UserDetails) {
    UserDetails details=(UserDetails)authentication.getPrincipal();
    String username=details.getUsername();
    for (    PhotoInfo photoInfo : getPhotos()) {
      if (id.equals(photoInfo.getId()) && username.equals(photoInfo.getUserId())) {
        URL resourceURL=getClass().getResource(photoInfo.getResourceURL());
        if (resourceURL != null) {
          try {
            return resourceURL.openStream();
          }
 catch (          IOException e) {
          }
        }
      }
    }
  }
  return null;
}
