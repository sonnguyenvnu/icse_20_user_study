/** 
 * Prints out the internal state of the policy. 
 */
private void printLirs(){
  System.out.println("** LIRS stack TOP **");
  for (Node n=headS.nextS; n != headS; n=n.nextS) {
    checkState(n.isInS);
    if (n.status == Status.HIR_NON_RESIDENT) {
      System.out.println("<NR> " + n.key);
    }
 else     if (n.status == Status.HIR_RESIDENT) {
      System.out.println("<RH> " + n.key);
    }
 else {
      System.out.println("<RL> " + n.key);
    }
  }
  System.out.println("** LIRS stack BOTTOM **");
  System.out.println("\n** LIRS queue END **");
  for (Node n=headQ.nextQ; n != headQ; n=n.nextQ) {
    checkState(n.isInQ);
    System.out.println(n.key);
  }
  System.out.println("** LIRS queue front **");
  System.out.println("\nLIRS EVICTED PAGE sequence:");
  for (int i=0; i < evicted.size(); i++) {
    System.out.println("<" + i + "> " + evicted.get(i));
  }
}
