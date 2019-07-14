private static ByteArray generate() throws IOException {
  int preType=5;
  int preChar=0;
  List<int[]> typeList=new LinkedList<int[]>();
  for (int i=0; i <= Character.MAX_VALUE; ++i) {
    int type=TextUtility.charType((char)i);
    if (type != preType) {
      int[] array=new int[3];
      array[0]=preChar;
      array[1]=i - 1;
      array[2]=preType;
      typeList.add(array);
      preChar=i;
    }
    preType=type;
  }
{
    int[] array=new int[3];
    array[0]=preChar;
    array[1]=(int)Character.MAX_VALUE;
    array[2]=preType;
    typeList.add(array);
  }
  DataOutputStream out=new DataOutputStream(new FileOutputStream(HanLP.Config.CharTypePath));
  for (  int[] array : typeList) {
    out.writeChar(array[0]);
    out.writeChar(array[1]);
    out.writeByte(array[2]);
  }
  out.close();
  ByteArray byteArray=ByteArray.createByteArray(HanLP.Config.CharTypePath);
  return byteArray;
}
