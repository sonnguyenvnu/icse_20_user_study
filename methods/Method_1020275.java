public int map(int x,int y){
  int px=(int)(transm[0] * x + transm[1] * y + transm[2]);
  int py=(int)(transm[3] * x + transm[4] * y + transm[5]);
  int idx=px + (py * width);
  if (idx > 0 && idx < imagedata.length) {
    return colors[imagedata[idx] & 1];
  }
  return 0;
}
