@Override public void newpath(){
  append("0 setlinewidth",true);
  append("[] 0 setdash",true);
  appendColor(getColor());
  append("newpath",true);
}
