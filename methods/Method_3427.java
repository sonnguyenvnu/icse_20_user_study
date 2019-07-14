public void segment(String text,Instance instance,List<String> output){
  int[] tagArray=instance.tagArray;
  model.viterbiDecode(instance,tagArray);
  StringBuilder result=new StringBuilder();
  result.append(text.charAt(0));
  for (int i=1; i < tagArray.length; i++) {
    if (tagArray[i] == CWSTagSet.B || tagArray[i] == CWSTagSet.S) {
      output.add(result.toString());
      result.setLength(0);
    }
    result.append(text.charAt(i));
  }
  if (result.length() != 0) {
    output.add(result.toString());
  }
}
