static int solve(int n,int p){
  int pagesFromFront=p / 2;
  int pagesFromBack=n / 2 - p / 2;
  return Math.min(pagesFromFront,pagesFromBack);
}
