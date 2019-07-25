public static void main(String args[]){
  Scanner sc=new Scanner(System.in);
  Node head=null;
  int T=sc.nextInt();
  while (T-- > 0) {
    int ele=sc.nextInt();
    head=insert(head,ele);
  }
  head=removeDuplicates(head);
  display(head);
}
