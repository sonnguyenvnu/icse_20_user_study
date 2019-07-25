private static void process(String fileName,String find,boolean head,boolean tail,long start,int lines,boolean quiet) throws IOException {
  RandomAccessFile file=new RandomAccessFile(fileName,"r");
  long length=file.length();
  if (head) {
    file.seek(start);
    list(start,"Head",readLines(file,lines));
  }
  if (find != null) {
    file.seek(start);
    long pos=find(file,find.getBytes(),quiet);
    if (pos >= 0) {
      file.seek(pos);
      list(pos,"Found " + find,readLines(file,lines));
    }
  }
  if (tail) {
    long pos=length - 100L * lines;
    ArrayList<String> list=null;
    while (pos > 0) {
      file.seek(pos);
      list=readLines(file,Integer.MAX_VALUE);
      if (list.size() > lines) {
        break;
      }
      pos-=100L * lines;
    }
    list.remove(0);
    while (list.size() > lines) {
      list.remove(0);
    }
    list(pos,"Tail",list);
  }
}
