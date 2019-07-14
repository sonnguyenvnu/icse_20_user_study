private void convert(String txtFile,String binFile) throws IOException {
  TagSet tagSet=new TagSet(TaskType.CLASSIFICATION);
  IOUtil.LineIterator lineIterator=new IOUtil.LineIterator(txtFile);
  if (!lineIterator.hasNext())   throw new IOException("????");
  logger.info(lineIterator.next());
  logger.info(lineIterator.next());
  int maxid=Integer.parseInt(lineIterator.next().substring("maxid:".length()).trim());
  logger.info(lineIterator.next());
  lineIterator.next();
  String line;
  while ((line=lineIterator.next()).length() != 0) {
    tagSet.add(line);
  }
  tagSet.type=guessModelType(tagSet);
switch (tagSet.type) {
case CWS:
    tagSet=new CWSTagSet(tagSet.idOf("B"),tagSet.idOf("M"),tagSet.idOf("E"),tagSet.idOf("S"));
  break;
case NER:
tagSet=new NERTagSet(tagSet.idOf("O"),tagSet.tags());
break;
}
tagSet.lock();
this.featureMap=new MutableFeatureMap(tagSet);
FeatureMap featureMap=this.featureMap;
final int sizeOfTagSet=tagSet.size();
TreeMap<String,FeatureFunction> featureFunctionMap=new TreeMap<String,FeatureFunction>();
TreeMap<Integer,FeatureFunction> featureFunctionList=new TreeMap<Integer,FeatureFunction>();
ArrayList<FeatureTemplate> featureTemplateList=new ArrayList<FeatureTemplate>();
float[][] matrix=null;
while ((line=lineIterator.next()).length() != 0) {
if (!"B".equals(line)) {
FeatureTemplate featureTemplate=FeatureTemplate.create(line);
featureTemplateList.add(featureTemplate);
}
 else {
matrix=new float[sizeOfTagSet][sizeOfTagSet];
}
}
this.featureTemplateArray=featureTemplateList.toArray(new FeatureTemplate[0]);
int b=-1;
if (matrix != null) {
String[] args=lineIterator.next().split(" ",2);
b=Integer.valueOf(args[0]);
featureFunctionList.put(b,null);
}
while ((line=lineIterator.next()).length() != 0) {
String[] args=line.split(" ",2);
char[] charArray=args[1].toCharArray();
FeatureFunction featureFunction=new FeatureFunction(charArray,sizeOfTagSet);
featureFunctionMap.put(args[1],featureFunction);
featureFunctionList.put(Integer.parseInt(args[0]),featureFunction);
}
for (Map.Entry<Integer,FeatureFunction> entry : featureFunctionList.entrySet()) {
int fid=entry.getKey();
FeatureFunction featureFunction=entry.getValue();
if (fid == b) {
for (int i=0; i < sizeOfTagSet; i++) {
for (int j=0; j < sizeOfTagSet; j++) {
  matrix[i][j]=Float.parseFloat(lineIterator.next());
}
}
}
 else {
for (int i=0; i < sizeOfTagSet; i++) {
featureFunction.w[i]=Double.parseDouble(lineIterator.next());
}
}
}
if (lineIterator.hasNext()) {
logger.warning("???????????????" + txtFile);
}
lineIterator.close();
logger.info("?????????????");
int transitionFeatureOffset=(sizeOfTagSet + 1) * sizeOfTagSet;
parameter=new float[transitionFeatureOffset + featureFunctionMap.size() * sizeOfTagSet];
if (matrix != null) {
for (int i=0; i < sizeOfTagSet; ++i) {
for (int j=0; j < sizeOfTagSet; ++j) {
parameter[i * sizeOfTagSet + j]=matrix[i][j];
}
}
}
for (Map.Entry<Integer,FeatureFunction> entry : featureFunctionList.entrySet()) {
int id=entry.getKey();
FeatureFunction f=entry.getValue();
if (f == null) continue;
String feature=new String(f.o);
for (int tid=0; tid < featureTemplateList.size(); tid++) {
FeatureTemplate template=featureTemplateList.get(tid);
Iterator<String> iterator=template.delimiterList.iterator();
String header=iterator.next();
if (feature.startsWith(header)) {
int fid=featureMap.idOf(feature.substring(header.length()) + tid);
for (int i=0; i < sizeOfTagSet; ++i) {
  parameter[fid * sizeOfTagSet + i]=(float)f.w[i];
}
break;
}
}
}
DataOutputStream out=new DataOutputStream(IOUtil.newOutputStream(binFile));
save(out);
out.writeInt(featureTemplateList.size());
for (FeatureTemplate template : featureTemplateList) {
template.save(out);
}
out.close();
}
