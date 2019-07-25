public static void report(ChronicleHashCorruption.Listener corruptionListener,ChronicleHashCorruptionImpl corruption,int segmentIndex,Supplier<String> messageSupplier){
  corruption.set(segmentIndex,messageSupplier,null);
  corruptionListener.onCorruption(corruption);
}
