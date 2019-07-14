private static String readKey(String key){
  Scanner stdin=new Scanner(System.in);
  System.out.println("Choose a Spider demo:");
  for (  Map.Entry<String,Class> classEntry : clazzMap.entrySet()) {
    System.out.println(classEntry.getKey() + "\t" + classEntry.getValue() + "\t" + urlMap.get(classEntry.getKey()));
  }
  while (key == null) {
    key=stdin.nextLine();
    if (clazzMap.get(key) == null) {
      System.out.println("Invalid choice!");
      key=null;
    }
  }
  return key;
}
