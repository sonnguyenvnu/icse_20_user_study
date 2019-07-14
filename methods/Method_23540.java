private void parsePathCode(int what){
  if (vertexCodeCount == vertexCodes.length) {
    vertexCodes=PApplet.expand(vertexCodes);
  }
  vertexCodes[vertexCodeCount++]=what;
}
