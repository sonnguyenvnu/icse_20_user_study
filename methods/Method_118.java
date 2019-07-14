public void moveTopTo(Tower t){
  int top=disks.pop();
  t.add(top);
  System.out.println("Move disk " + top + " from " + index() + " to " + t.index());
}
