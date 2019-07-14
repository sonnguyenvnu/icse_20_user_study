/** 
 * ??
 * @param templFile     ????
 * @param trainFile     ????
 * @param modelFile     ????
 * @param textModelFile ?????????????
 * @param maxitr        ??????
 * @param freq          ??????
 * @param eta           ????
 * @param C             cost-factor
 * @param threadNum     ???
 * @param shrinkingSize
 * @param algorithm     ????
 * @return
 */
public boolean learn(String templFile,String trainFile,String modelFile,boolean textModelFile,int maxitr,int freq,double eta,double C,int threadNum,int shrinkingSize,Algorithm algorithm){
  if (eta <= 0) {
    System.err.println("eta must be > 0.0");
    return false;
  }
  if (C < 0.0) {
    System.err.println("C must be >= 0.0");
    return false;
  }
  if (shrinkingSize < 1) {
    System.err.println("shrinkingSize must be >= 1");
    return false;
  }
  if (threadNum <= 0) {
    System.err.println("thread must be  > 0");
    return false;
  }
  EncoderFeatureIndex featureIndex=new EncoderFeatureIndex(threadNum);
  List<TaggerImpl> x=new ArrayList<TaggerImpl>();
  if (!featureIndex.open(templFile,trainFile)) {
    System.err.println("Fail to open " + templFile + " " + trainFile);
  }
  BufferedReader br=null;
  try {
    InputStreamReader isr=new InputStreamReader(IOUtil.newInputStream(trainFile),"UTF-8");
    br=new BufferedReader(isr);
    int lineNo=0;
    while (true) {
      TaggerImpl tagger=new TaggerImpl(TaggerImpl.Mode.LEARN);
      tagger.open(featureIndex);
      TaggerImpl.ReadStatus status=tagger.read(br);
      if (status == TaggerImpl.ReadStatus.ERROR) {
        System.err.println("error when reading " + trainFile);
        return false;
      }
      if (!tagger.empty()) {
        if (!tagger.shrink()) {
          System.err.println("fail to build feature index ");
          return false;
        }
        tagger.setThread_id_(lineNo % threadNum);
        x.add(tagger);
      }
 else       if (status == TaggerImpl.ReadStatus.EOF) {
        break;
      }
 else {
        continue;
      }
      if (++lineNo % 100 == 0) {
        System.out.print(lineNo + ".. ");
      }
    }
    br.close();
  }
 catch (  IOException e) {
    System.err.println("train file " + trainFile + " does not exist.");
    return false;
  }
  featureIndex.shrink(freq,x);
  double[] alpha=new double[featureIndex.size()];
  Arrays.fill(alpha,0.0);
  featureIndex.setAlpha_(alpha);
  System.out.println("Number of sentences: " + x.size());
  System.out.println("Number of features:  " + featureIndex.size());
  System.out.println("Number of thread(s): " + threadNum);
  System.out.println("Freq:                " + freq);
  System.out.println("eta:                 " + eta);
  System.out.println("C:                   " + C);
  System.out.println("shrinking size:      " + shrinkingSize);
switch (algorithm) {
case CRF_L1:
    if (!runCRF(x,featureIndex,alpha,maxitr,C,eta,shrinkingSize,threadNum,true)) {
      System.err.println("CRF_L1 execute error");
      return false;
    }
  break;
case CRF_L2:
if (!runCRF(x,featureIndex,alpha,maxitr,C,eta,shrinkingSize,threadNum,false)) {
  System.err.println("CRF_L2 execute error");
  return false;
}
break;
case MIRA:
if (!runMIRA(x,featureIndex,alpha,maxitr,C,eta,shrinkingSize,threadNum)) {
System.err.println("MIRA execute error");
return false;
}
break;
default :
break;
}
if (!featureIndex.save(modelFile,textModelFile)) {
System.err.println("Failed to save model");
}
System.out.println("Done!");
return true;
}
