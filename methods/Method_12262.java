public BufferedImage createVerifyCodeRegister(){
  int width=80;
  int height=32;
  BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
  Graphics g=image.getGraphics();
  g.setColor(new Color(0xDCDCDC));
  g.fillRect(0,0,width,height);
  g.setColor(Color.black);
  g.drawRect(0,0,width - 1,height - 1);
  Random rdm=new Random();
  for (int i=0; i < 50; i++) {
    int x=rdm.nextInt(width);
    int y=rdm.nextInt(height);
    g.drawOval(x,y,0,0);
  }
  String verifyCode=generateVerifyCode(rdm);
  g.setColor(new Color(0,100,0));
  g.setFont(new Font("Candara",Font.BOLD,24));
  g.drawString(verifyCode,8,24);
  g.dispose();
  int rnd=calc(verifyCode);
  redisService.set(MiaoshaKey.getMiaoshaVerifyCodeRegister,"regitser",rnd);
  return image;
}
