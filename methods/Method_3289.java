public Tagger createTagger(){
  if (featureIndex_ == null) {
    return null;
  }
  TaggerImpl tagger=new TaggerImpl(TaggerImpl.Mode.TEST);
  tagger.open(featureIndex_,nbest_,vlevel_);
  return tagger;
}
