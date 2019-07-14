public String getIndex(String[] idxStr,int cur,TaggerImpl tagger){
  int row=Integer.valueOf(idxStr[0]);
  int col=Integer.valueOf(idxStr[1]);
  int pos=row + cur;
  if (row < -EOS.length || row > EOS.length || col < 0 || col >= tagger.xsize()) {
    return null;
  }
  if (checkMaxXsize_) {
    max_xsize_=Math.max(max_xsize_,col + 1);
  }
  if (pos < 0) {
    return BOS[-pos - 1];
  }
 else   if (pos >= tagger.size()) {
    return EOS[pos - tagger.size()];
  }
 else {
    return tagger.x(pos,col);
  }
}
