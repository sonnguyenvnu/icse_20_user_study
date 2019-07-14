public static Instrument getInstance(){
  if (mInstrument == null) {
    mInstrument=new Instrument();
  }
  return mInstrument;
}
