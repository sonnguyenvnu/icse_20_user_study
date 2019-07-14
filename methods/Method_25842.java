private void fileReaderFix(Description.Builder description,VisitorState state,Tree arg,Tree toReplace){
  for (  CharsetFix charset : CharsetFix.values()) {
    if (shouldUseGuava(state)) {
      description.addFix(guavaFileReaderFix(state,arg,toReplace,charset));
    }
 else {
      description.addFix(nioFileReaderFix(state,arg,toReplace,charset));
    }
  }
}
