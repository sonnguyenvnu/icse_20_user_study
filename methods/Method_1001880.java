private String definecolor(String name,Color color){
  return "\\definecolor{" + name + "}{RGB}{" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "}";
}
