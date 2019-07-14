@RequestMapping("/photos/{photoId}") public ResponseEntity<byte[]> getPhoto(@PathVariable("photoId") String id) throws IOException {
  InputStream photo=photoService.loadPhoto(id);
  if (photo == null) {
    return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
  }
 else {
    ByteArrayOutputStream out=new ByteArrayOutputStream();
    byte[] buffer=new byte[1024];
    int len=photo.read(buffer);
    while (len >= 0) {
      out.write(buffer,0,len);
      len=photo.read(buffer);
    }
    HttpHeaders headers=new HttpHeaders();
    headers.set("Content-Type","image/jpeg");
    return new ResponseEntity<byte[]>(out.toByteArray(),headers,HttpStatus.OK);
  }
}
