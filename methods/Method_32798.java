private static void addToEnvIfPresent(List<String> environment,String name){
  String value=System.getenv(name);
  if (value != null) {
    environment.add(name + "=" + value);
  }
}
