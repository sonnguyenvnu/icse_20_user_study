@GetMapping("/code/image") public void createCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
  ImageCode imageCode=createImageCode();
  sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY_IMAGE_CODE,imageCode);
  ImageIO.write(imageCode.getImage(),"jpeg",response.getOutputStream());
}
