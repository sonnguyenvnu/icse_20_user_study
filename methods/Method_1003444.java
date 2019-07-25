private static void list(long pos,String header,ArrayList<String> list){
  System.out.println("-----------------------------------------------");
  System.out.println("[" + pos + "]: " + header);
  System.out.println("-----------------------------------------------");
  for (  String l : list) {
    System.out.println(l);
  }
  System.out.println("-----------------------------------------------");
}
