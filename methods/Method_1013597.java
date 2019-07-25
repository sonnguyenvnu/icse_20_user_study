public void set(final Selection c) throws IOException {
  final File udcFile=new File(PATH);
  udcFile.createNewFile();
  try (BufferedWriter br=new BufferedWriter(new FileWriter(udcFile))){
    br.write(c.toString() + "\n");
  }
 catch (  IOException e) {
    throw e;
  }
}
