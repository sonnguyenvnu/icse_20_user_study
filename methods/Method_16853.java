private static void printNode(int val){
  StringBuilder res=new StringBuilder(val + "<");
  int spaceNum=space.length() - res.length();
  for (int i=0; i < spaceNum; i++) {
    res.append(" ");
  }
  System.out.println(res);
}
