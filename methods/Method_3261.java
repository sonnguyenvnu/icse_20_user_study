public static boolean run(String[] args){
  Option option=new Option();
  List<String> unkownArgs=null;
  try {
    unkownArgs=Args.parse(option,args,false);
  }
 catch (  IllegalArgumentException e) {
    System.err.println(e.getMessage());
    Args.usage(option);
    return false;
  }
  boolean convert=option.convert;
  boolean convertToText=option.convert_to_text;
  String[] restArgs=unkownArgs.toArray(new String[0]);
  if (option.help || ((convertToText || convert) && restArgs.length != 2) || (!convert && !convertToText && restArgs.length != 3)) {
    Args.usage(option);
    return option.help;
  }
  int freq=option.freq;
  int maxiter=option.maxiter;
  double C=option.cost;
  double eta=option.eta;
  boolean textmodel=option.textmodel;
  int threadNum=option.thread;
  if (threadNum <= 0) {
    threadNum=Runtime.getRuntime().availableProcessors();
  }
  int shrinkingSize=option.shrinking_size;
  String algorithm=option.algorithm;
  algorithm=algorithm.toLowerCase();
  Encoder.Algorithm algo=Encoder.Algorithm.CRF_L2;
  if (algorithm.equals("crf") || algorithm.equals("crf-l2")) {
    algo=Encoder.Algorithm.CRF_L2;
  }
 else   if (algorithm.equals("crf-l1")) {
    algo=Encoder.Algorithm.CRF_L1;
  }
 else   if (algorithm.equals("mira")) {
    algo=Encoder.Algorithm.MIRA;
  }
 else {
    System.err.println("unknown algorithm: " + algorithm);
    return false;
  }
  if (convert) {
    EncoderFeatureIndex featureIndex=new EncoderFeatureIndex(1);
    if (!featureIndex.convert(restArgs[0],restArgs[1])) {
      System.err.println("fail to convert text model");
      return false;
    }
  }
 else   if (convertToText) {
    DecoderFeatureIndex featureIndex=new DecoderFeatureIndex();
    if (!featureIndex.convert(restArgs[0],restArgs[1])) {
      System.err.println("fail to convert binary model");
      return false;
    }
  }
 else {
    Encoder encoder=new Encoder();
    if (!encoder.learn(restArgs[0],restArgs[1],restArgs[2],textmodel,maxiter,freq,eta,C,threadNum,shrinkingSize,algo)) {
      System.err.println("fail to learn model");
      return false;
    }
  }
  return true;
}
