private boolean isLineTop(int prevWidth,int currentWidth,int line,int count,int cornerRest){
  return line == 0 || !(line < 0 || line >= count) && findMaxWidthAroundLine(line - 1) + cornerRest * 3 < prevWidth;
}
