int getrand(int x){
  int q=random.nextInt();
  if (q < 0)   q=-q;
  return q % x;
}
