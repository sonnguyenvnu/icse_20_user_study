public static void display(Node head){
  Node start=head;
  while (start != null) {
    System.out.print(start.data + " ");
    start=start.next;
  }
}
