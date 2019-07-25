private static String solve(int x,int y,int z){
  int A_to_C=Math.abs(z - x);
  int B_to_C=Math.abs(z - y);
  if (A_to_C < B_to_C) {
    return "Cat A";
  }
 else   if (A_to_C > B_to_C) {
    return "Cat B";
  }
 else {
    return "Mouse C";
  }
}
