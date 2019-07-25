public static void main(String args[]){
  Scanner sc=new Scanner(System.in);
  int T=sc.nextInt();
  Node root=null;
  while (T-- > 0) {
    int data=sc.nextInt();
    root=insert(root,data);
  }
  int height=getHeight(root);
  System.out.println(height);
}
