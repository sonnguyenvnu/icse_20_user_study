/** 
 * Call to explicitly go to the next page from within a single draw().
 */
public void nextPage(){
  PStyle savedStyle=getStyle();
  endDraw();
  g2.dispose();
  try {
    document.newPage();
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  g2=createGraphics();
  beginDraw();
  style(savedStyle);
}
