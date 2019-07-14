public void beginDraw(){
  if (writer == null) {
    try {
      writer=new PrintWriter(new FileWriter(file));
    }
 catch (    IOException e) {
      throw new RuntimeException(e);
    }
    writeHeader();
  }
}
