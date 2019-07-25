@Override public boolean equals(Object obj){
  return (obj instanceof PartOfSpeechAttributeImpl) && ((PartOfSpeechAttributeImpl)obj).getPOS() == this.pos;
}
