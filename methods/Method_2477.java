public double weight(int feature,int tf){
  if (feature >= df.length)   System.err.println(feature);
  return Math.log10(tf + 1) * (Math.log10((double)numDocs / df[feature] + 1));
}
