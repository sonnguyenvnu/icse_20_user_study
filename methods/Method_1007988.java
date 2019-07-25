@Override public int erase(final String credsStore,final String registry) throws IOException, InterruptedException {
  final Process process=exec("erase",credsStore);
  try (final Writer outStreamWriter=new OutputStreamWriter(process.getOutputStream(),StandardCharsets.UTF_8)){
    try (final BufferedWriter writer=new BufferedWriter(outStreamWriter)){
      writer.write(registry);
      writer.newLine();
      writer.flush();
    }
   }
   return process.waitFor();
}
