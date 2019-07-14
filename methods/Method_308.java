private void updateCondition(){
  if (Thread.currentThread().getName().equals("t1")) {
    type=2;
  }
 else   if (Thread.currentThread().getName().equals("t2")) {
    type=3;
  }
 else   if (Thread.currentThread().getName().equals("t3")) {
    type=4;
  }
 else   if (Thread.currentThread().getName().equals("t4")) {
    type=1;
  }
}
