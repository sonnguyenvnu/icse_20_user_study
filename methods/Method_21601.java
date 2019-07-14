@Override public String getText(){
  StringBuilder sb=new StringBuilder();
  sb.append("{ts '");
  sb.append(this.text);
  sb.append("'}");
  return sb.toString();
}
