@RequestMapping(value="/photos",params="format=json") public ResponseEntity<String> getJsonPhotos(Principal principal){
  Collection<PhotoInfo> photos=getPhotoService().getPhotosForCurrentUser(principal.getName());
  StringBuilder out=new StringBuilder();
  out.append("{ \"photos\" : [ ");
  Iterator<PhotoInfo> photosIt=photos.iterator();
  while (photosIt.hasNext()) {
    PhotoInfo photo=photosIt.next();
    out.append(String.format("{ \"id\" : \"%s\" , \"name\" : \"%s\" }",photo.getId(),photo.getName()));
    if (photosIt.hasNext()) {
      out.append(" , ");
    }
  }
  out.append("] }");
  HttpHeaders headers=new HttpHeaders();
  headers.set("Content-Type","application/javascript");
  return new ResponseEntity<String>(out.toString(),headers,HttpStatus.OK);
}
