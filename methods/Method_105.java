int makeChange(int n){
  int[] denoms={25,10,5,1};
  int[][] map=new int[n + 1][denoms.length];
  return makeChange(n,denoms,0,map);
}
