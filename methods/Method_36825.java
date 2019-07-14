@Override public boolean isValid(){
  return super.isValid() && mCells.size() > 0 && (style instanceof DelegateStyle && ((DelegateStyle)style).cardInfos.size() > 0);
}
