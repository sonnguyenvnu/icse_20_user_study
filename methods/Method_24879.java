public String apply(){
  final int inLength=input.length();
  final StringBuilder output=new StringBuilder(inLength);
  buildIfNeeded(inLength);
  outMap.stream().filter(outEdit -> outEdit.toLength > 0).forEach(outEdit -> {
    if (outEdit.outputText != null) {
      output.append(outEdit.outputText);
    }
 else {
      output.append(input,outEdit.fromOffset,outEdit.fromOffset + outEdit.fromLength);
    }
  }
);
  return output.toString();
}
