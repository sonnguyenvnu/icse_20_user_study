private void dfs(char[][] image,int r,int c){
  done[r][c]=true;
  for (int i=0; i < 4; i++) {
    int newR=r + R[i];
    int newC=c + C[i];
    if (newR >= 0 && newR < image.length && newC >= 0 && newC < image[0].length && !done[newR][newC]) {
      if (image[newR][newC] == '1') {
        maxR=Math.max(maxR,newR);
        minR=Math.min(minR,newR);
        maxC=Math.max(maxC,newC);
        minC=Math.min(minC,newC);
        dfs(image,newR,newC);
      }
    }
  }
}
