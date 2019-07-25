@Override public void reset(CharSequence input){
  boolean found=false;
  for (int i=0; i < input.length(); i++) {
    if (input.charAt(i) == ':' || input.charAt(i) == '<') {
      found=true;
      break;
    }
  }
  if (found) {
    super.reset(input);
  }
}
