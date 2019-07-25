public void execute() throws Pausable {
  while (true) {
    StringBuffer sb=mymb.get();
    if (nextmb == null) {
      System.out.print(sb);
      System.out.println("world");
      System.exit(0);
    }
 else {
      sb.append("hello ");
      nextmb.put(sb);
    }
  }
}
