private void loadDictFile(DictSegment dict,Path file,boolean critical,String name){
  try (InputStream is=new FileInputStream(file.toFile())){
    BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"),512);
    String word=br.readLine();
    if (word != null) {
      if (word.startsWith("\uFEFF"))       word=word.substring(1);
      for (; word != null; word=br.readLine()) {
        word=word.trim();
        if (word.isEmpty())         continue;
        dict.fillSegment(word.toCharArray());
      }
    }
  }
 catch (  FileNotFoundException e) {
    logger.error("ik-analyzer: " + name + " not found",e);
    if (critical)     throw new RuntimeException("ik-analyzer: " + name + " not found!!!",e);
  }
catch (  IOException e) {
    logger.error("ik-analyzer: " + name + " loading failed",e);
  }
}
