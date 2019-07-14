static protected PFont createFont(String name,int weight,float size,boolean smooth){
  java.awt.Font baseFont=new java.awt.Font(name,weight,(int)size);
  return new PFont(baseFont.deriveFont(size),smooth,null);
}
