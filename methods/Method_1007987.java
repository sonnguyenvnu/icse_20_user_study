@Override public int store(final String credsStore,final DockerCredentialHelperAuth auth) throws IOException, InterruptedException {
  final Process process=exec("store",credsStore);
  try (final Writer outStreamWriter=new OutputStreamWriter(process.getOutputStream(),StandardCharsets.UTF_8)){
    try (final BufferedWriter writer=new BufferedWriter(outStreamWriter)){
      writer.write(mapper.writeValueAsString(auth));
      writer.newLine();
      writer.flush();
    }
   }
   return process.waitFor();
}
