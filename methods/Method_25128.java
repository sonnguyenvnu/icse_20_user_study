public CharSequence getAsSequence(){
  return CharBuffer.wrap(sourceBuilder).asReadOnlyBuffer();
}
