static public void setIcon(String... icons){
  PJOGL.icons=new String[icons.length];
  PApplet.arrayCopy(icons,PJOGL.icons);
}
