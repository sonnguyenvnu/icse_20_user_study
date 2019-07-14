private boolean isLineBottom(int nextWidth,int currentWidth,int line,int count,int cornerRest){
  return line == count - 1 || !(line < 0 || line > count - 1) && findMaxWidthAroundLine(line + 1) + cornerRest * 3 < nextWidth;
}
