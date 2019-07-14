public static ArrayList<Options> getAllPossibleOptions(Options option){
  ArrayList<Options> options=new ArrayList<Options>();
  options.add(option);
  ArrayList<Options> tmp=new ArrayList<Options>();
  for (  Options opt : options) {
    Options o1=opt.clone();
    o1.labeled=true;
    Options o2=opt.clone();
    o2.labeled=false;
    tmp.add(o1);
    tmp.add(o2);
  }
  options=tmp;
  tmp=new ArrayList<Options>();
  for (  Options opt : options) {
    Options o1=opt.clone();
    o1.lowercase=true;
    Options o2=opt.clone();
    o2.lowercase=false;
    tmp.add(o1);
    tmp.add(o2);
  }
  options=tmp;
  tmp=new ArrayList<Options>();
  for (  Options opt : options) {
    Options o1=opt.clone();
    o1.useExtendedFeatures=true;
    Options o2=opt.clone();
    o2.useExtendedFeatures=false;
    tmp.add(o1);
    tmp.add(o2);
  }
  options=tmp;
  tmp=new ArrayList<Options>();
  for (  Options opt : options) {
    Options o1=opt.clone();
    o1.useDynamicOracle=true;
    Options o2=opt.clone();
    o2.useDynamicOracle=false;
    tmp.add(o1);
    tmp.add(o2);
  }
  options=tmp;
  tmp=new ArrayList<Options>();
  for (  Options opt : options) {
    Options o1=opt.clone();
    o1.useMaxViol=true;
    Options o2=opt.clone();
    o2.useMaxViol=false;
    tmp.add(o1);
    tmp.add(o2);
  }
  options=tmp;
  tmp=new ArrayList<Options>();
  for (  Options opt : options) {
    Options o1=opt.clone();
    o1.useRandomOracleSelection=true;
    Options o2=opt.clone();
    o2.useRandomOracleSelection=false;
    tmp.add(o1);
    tmp.add(o2);
  }
  options=tmp;
  tmp=new ArrayList<Options>();
  for (  Options opt : options) {
    Options o1=opt.clone();
    o1.rootFirst=true;
    Options o2=opt.clone();
    o2.rootFirst=false;
    tmp.add(o1);
    tmp.add(o2);
  }
  options=tmp;
  return options;
}
