public void writeTo(Output output,int number,Date value,boolean repeated) throws IOException {
  output.writeFixed64(number,value.getTime(),repeated);
}
