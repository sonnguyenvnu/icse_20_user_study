@GetMapping("/code/image") public void createCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
  ImageCode imageCode=createImageCode();
  ImageCode codeInRedis=new ImageCode(null,imageCode.getCode(),imageCode.getExpireTime());
  sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY_IMAGE_CODE,codeInRedis);
  ImageIO.write(imageCode.getImage(),"jpeg",response.getOutputStream());
}
