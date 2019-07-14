private boolean regionsConnect(Region lower,Region upper){
  return lower != null && upper != null && lower.endOffset == upper.startOffset;
}
