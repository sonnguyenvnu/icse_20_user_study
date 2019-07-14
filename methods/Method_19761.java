private ImageCode createImageCode(){
  int width=100;
  int height=36;
  int length=4;
  int expireIn=60;
  BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
  Graphics g=image.getGraphics();
  Random random=new Random();
  g.setColor(getRandColor(200,250));
  g.fillRect(0,0,width,height);
  g.setFont(new Font("Times New Roman",Font.ITALIC,20));
  g.setColor(getRandColor(160,200));
  for (int i=0; i < 155; i++) {
    int x=random.nextInt(width);
    int y=random.nextInt(height);
    int xl=random.nextInt(12);
    int yl=random.nextInt(12);
    g.drawLine(x,y,x + xl,y + yl);
  }
  StringBuilder sRand=new StringBuilder();
  for (int i=0; i < length; i++) {
    String rand=String.valueOf(random.nextInt(10));
    sRand.append(rand);
    g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));
    g.drawString(rand,13 * i + 6,16);
  }
  g.dispose();
  return new ImageCode(image,sRand.toString(),expireIn);
}
