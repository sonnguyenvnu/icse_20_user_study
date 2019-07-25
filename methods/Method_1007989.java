@Override public Map<String,String> list(final String credsStore) throws IOException {
  final Process process=exec("list",credsStore);
  try (final InputStreamReader reader=new InputStreamReader(process.getInputStream(),StandardCharsets.UTF_8)){
    try (BufferedReader input=new BufferedReader(reader)){
      final String serverAuthDetails=input.readLine();
      if ("The specified item could not be found in the keychain.".equals(serverAuthDetails)) {
        return null;
      }
      return mapper.readValue(serverAuthDetails,new TypeReference<Map<String,String>>(){
      }
);
    }
   }
 }
