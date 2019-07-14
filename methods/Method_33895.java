@RequestMapping(value="/photos",params="format=xml") public ResponseEntity<String> getXmlPhotos(Principal principal){
  Collection<PhotoInfo> photos=photoService.getPhotosForCurrentUser(principal.getName());
  StringBuilder out=new StringBuilder();
  out.append("<photos>");
  for (  PhotoInfo photo : photos) {
    out.append(String.format("<photo id=\"%s\" name=\"%s\"/>",photo.getId(),photo.getName()));
  }
  out.append("</photos>");
  HttpHeaders headers=new HttpHeaders();
  headers.set("Content-Type","application/xml");
  return new ResponseEntity<String>(out.toString(),headers,HttpStatus.OK);
}
