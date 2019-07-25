private static void combine(Log log,InputStream in,OutputStream out,String tempFileName) throws IOException {
  int bufferSize=16 * 1024 * 1024;
  DataOutputStream tempOut=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(tempFileName),1024 * 1024));
  DataInputStream dataIn=new DataInputStream(in);
  byte[] header=new byte[4];
  dataIn.readFully(header);
  if (!Arrays.equals(header,HEADER)) {
    tempOut.close();
    throw new IOException("Invalid header");
  }
  long size=readVarLong(dataIn);
  long outPos=0;
  List<Long> segmentStart=new ArrayList<>();
  boolean end=false;
  log.setRange(0,30,size);
  while (!end) {
    int segmentSize=0;
    TreeMap<Long,byte[]> map=new TreeMap<>();
    while (segmentSize < bufferSize) {
      Chunk c=Chunk.read(dataIn,false);
      if (c == null) {
        end=true;
        break;
      }
      int length=c.value.length;
      log.printProgress(length);
      segmentSize+=length;
      for (      long x : c.idList) {
        map.put(x,c.value);
      }
    }
    if (map.size() == 0) {
      break;
    }
    segmentStart.add(outPos);
    for (    Long x : map.keySet()) {
      outPos+=writeVarLong(tempOut,x);
      outPos+=writeVarLong(tempOut,0);
      byte[] v=map.get(x);
      outPos+=writeVarLong(tempOut,v.length);
      tempOut.write(v);
      outPos+=v.length;
    }
    outPos+=writeVarLong(tempOut,0);
  }
  tempOut.close();
  long tempSize=new File(tempFileName).length();
  size=outPos;
  int blockSize=64;
  boolean merge=false;
  while (segmentStart.size() > blockSize) {
    merge=true;
    log.setRange(30,50,tempSize);
    log.println();
    log.println("Merging " + segmentStart.size() + " segments " + blockSize + ":1");
    ArrayList<Long> segmentStart2=new ArrayList<>();
    outPos=0;
    DataOutputStream tempOut2=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(tempFileName + ".b"),1024 * 1024));
    while (segmentStart.size() > 0) {
      segmentStart2.add(outPos);
      int s=Math.min(segmentStart.size(),blockSize);
      List<Long> start=segmentStart.subList(0,s);
      TreeSet<ChunkStream> segmentIn=new TreeSet<>();
      long read=openSegments(start,segmentIn,tempFileName,false);
      log.printProgress(read);
      Iterator<Chunk> it=merge(segmentIn,log);
      while (it.hasNext()) {
        Chunk c=it.next();
        outPos+=writeVarLong(tempOut2,c.idList.get(0));
        outPos+=writeVarLong(tempOut2,0);
        outPos+=writeVarLong(tempOut2,c.value.length);
        tempOut2.write(c.value);
        outPos+=c.value.length;
      }
      outPos+=writeVarLong(tempOut2,0);
      segmentStart=segmentStart.subList(s,segmentStart.size());
    }
    segmentStart=segmentStart2;
    tempOut2.close();
    tempSize=new File(tempFileName).length();
    new File(tempFileName).delete();
    tempFileName+=".b";
  }
  if (merge) {
    log.println();
    log.println("Combining " + segmentStart.size() + " segments");
  }
  TreeSet<ChunkStream> segmentIn=new TreeSet<>();
  DataOutputStream dataOut=new DataOutputStream(out);
  log.setRange(50,100,size);
  long read=openSegments(segmentStart,segmentIn,tempFileName,false);
  log.printProgress(read);
  Iterator<Chunk> it=merge(segmentIn,log);
  while (it.hasNext()) {
    dataOut.write(it.next().value);
  }
  new File(tempFileName).delete();
  dataOut.flush();
}
