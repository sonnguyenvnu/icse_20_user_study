public void add(int d){
  if (!disks.isEmpty() && disks.peek() <= d) {
    System.out.println("Error placing disk " + d);
  }
 else {
    disks.push(d);
  }
}
