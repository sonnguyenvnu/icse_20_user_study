/** 
 * ?????
 * @throws CommonException
 * @throws IOException
 */
@RequestMapping("/authcode") public void captcha() throws CommonException, IOException {
  response.setHeader("Cache-Control","no-store, no-cache");
  response.setContentType("image/jpeg");
  String text=producer.createText();
  BufferedImage image=producer.createImage(text);
  session.setAttribute(Constants.KAPTCHA_SESSION_KEY,text);
  ServletOutputStream out=response.getOutputStream();
  ImageIO.write(image,"jpg",out);
  IOUtils.closeQuietly(out);
}
