/** 
 * Creates a avatar image with the specified hash string and size. <p> Refers to: https://github.com/superhj1987/awesome-identicon </p>
 * @param hash the specified hash string
 * @param size the specified size
 * @return buffered image
 */
public BufferedImage createAvatar(final String hash,final int size){
  final boolean[][] array=new boolean[6][5];
  for (int i=0; i < 6; i++) {
    for (int j=0; j < 5; j++) {
      array[i][j]=false;
    }
  }
  for (int i=0; i < hash.length(); i+=2) {
    final int s=i / 2;
    final boolean v=Math.random() > 0.5;
    if (s % 3 == 0) {
      array[s / 3][0]=v;
      array[s / 3][4]=v;
    }
 else     if (s % 3 == 1) {
      array[s / 3][1]=v;
      array[s / 3][3]=v;
    }
 else {
      array[s / 3][2]=v;
    }
  }
  final int ratio=Math.round(size / 5);
  final BufferedImage ret=new BufferedImage(ratio * 5,ratio * 5,BufferedImage.TYPE_3BYTE_BGR);
  final Graphics graphics=ret.getGraphics();
  graphics.setColor(new Color(Integer.parseInt(String.valueOf(hash.charAt(0)),16) * 16,Integer.parseInt(String.valueOf(hash.charAt(1)),16) * 16,Integer.parseInt(String.valueOf(hash.charAt(2)),16) * 16));
  graphics.fillRect(0,0,ret.getWidth(),ret.getHeight());
  graphics.setColor(new Color(Integer.parseInt(String.valueOf(hash.charAt(hash.length() - 1)),16) * 16,Integer.parseInt(String.valueOf(hash.charAt(hash.length() - 2)),16) * 16,Integer.parseInt(String.valueOf(hash.charAt(hash.length() - 3)),16) * 16));
  for (int i=0; i < 6; i++) {
    for (int j=0; j < 5; j++) {
      if (array[i][j]) {
        graphics.fillRect(j * ratio,i * ratio,ratio,ratio);
      }
    }
  }
  return ret;
}
