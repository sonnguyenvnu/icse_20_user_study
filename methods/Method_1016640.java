void traverse(final MessageUnpacker unpacker,final QueryResultModelPath queryResultPath,final int readAmount) throws IOException {
  int amount=0;
  while (unpacker.hasNext() && amount < readAmount) {
    MessageFormat format=unpacker.getNextFormat();
    ValueType type=format.getValueType();
    int length;
    ExtensionTypeHeader extension;
    Object o=null;
    byte[] dst;
    String addedName=null;
    Object addedObject=null;
switch (type) {
case NIL:
      unpacker.unpackNil();
    break;
case BOOLEAN:
  o=unpacker.unpackBoolean();
break;
case INTEGER:
switch (format) {
case UINT64:
o=unpacker.unpackBigInteger();
break;
case INT64:
case UINT32:
o=unpacker.unpackLong();
break;
default :
o=unpacker.unpackInt();
break;
}
break;
case FLOAT:
o=unpacker.unpackDouble();
break;
case STRING:
o=unpacker.unpackString();
lastStringNode=(String)o;
if ("name".equals(o) && queryResultPath.compareEndingPath("series")) {
queryResultPath.add("name",null);
}
 else if (queryResultPath.compareEndingPath("name")) {
queryResultPath.removeLast();
Series series=queryResultPath.getLastObject();
series.setName((String)o);
}
 else if (queryResultPath.compareEndingPath("tags")) {
queryResultPath.add("tagKey",o);
}
 else if (queryResultPath.compareEndingPath("tagKey")) {
String tagKey=queryResultPath.getLastObject();
queryResultPath.removeLast();
Map<String,String> tags=queryResultPath.getLastObject();
tags.put(tagKey,(String)o);
}
 else if (queryResultPath.compareEndingPath("columns")) {
List<String> columns=queryResultPath.getLastObject();
columns.add((String)o);
}
break;
case BINARY:
length=unpacker.unpackBinaryHeader();
dst=new byte[length];
unpacker.readPayload(dst);
break;
case ARRAY:
length=unpacker.unpackArrayHeader();
if (length > 0) {
if ("results".equals(lastStringNode)) {
QueryResult queryResult=queryResultPath.getLastObject();
List<Result> results=new ArrayList<>();
queryResult.setResults(results);
addedName="results";
addedObject=results;
}
 else if ("series".equals(lastStringNode) && queryResultPath.compareEndingPath("result")) {
Result result=queryResultPath.getLastObject();
List<Series> series=new ArrayList<>();
result.setSeries(series);
addedName="seriesList";
addedObject=series;
}
 else if ("columns".equals(lastStringNode) && queryResultPath.compareEndingPath("series")) {
Series series=queryResultPath.getLastObject();
List<String> columns=new ArrayList<>();
series.setColumns(columns);
addedName="columns";
addedObject=columns;
}
 else if ("values".equals(lastStringNode) && queryResultPath.compareEndingPath("series")) {
Series series=queryResultPath.getLastObject();
List<List<Object>> values=new ArrayList<>();
series.setValues(values);
addedName="values";
addedObject=values;
}
 else if (queryResultPath.compareEndingPath("values")) {
List<List<Object>> values=queryResultPath.getLastObject();
List<Object> value=new ArrayList<>();
values.add(value);
addedName="value";
addedObject=value;
}
if (addedName != null) {
queryResultPath.add(addedName,addedObject);
}
traverse(unpacker,queryResultPath,length);
if (addedName != null) {
queryResultPath.removeLast();
}
}
break;
case MAP:
length=unpacker.unpackMapHeader();
if (queryResultPath.compareEndingPath("results")) {
List<Result> results=queryResultPath.getLastObject();
Result result=new Result();
results.add(result);
addedName="result";
addedObject=result;
}
 else if (queryResultPath.compareEndingPath("seriesList")) {
List<Series> series=queryResultPath.getLastObject();
Series s=new Series();
series.add(s);
addedName="series";
addedObject=s;
}
 else if ("tags".equals(lastStringNode) && queryResultPath.compareEndingPath("series")) {
Series series=queryResultPath.getLastObject();
Map<String,String> tags=new HashMap<>();
series.setTags(tags);
addedName="tags";
addedObject=tags;
}
if (addedName != null) {
queryResultPath.add(addedName,addedObject);
}
for (int i=0; i < length; i++) {
traverse(unpacker,queryResultPath,1);
traverse(unpacker,queryResultPath,1);
}
if (addedName != null) {
queryResultPath.removeLast();
}
break;
case EXTENSION:
final int nanosStartIndex=8;
extension=unpacker.unpackExtensionTypeHeader();
if (extension.getType() == MSG_PACK_TIME_EXT_TYPE) {
dst=new byte[extension.getLength()];
unpacker.readPayload(dst);
ByteBuffer bf=ByteBuffer.wrap(dst,0,extension.getLength());
long epochSeconds=bf.getLong();
int nanosOffset=bf.getInt(nanosStartIndex);
o=TimeUnit.SECONDS.toNanos(epochSeconds) + nanosOffset;
}
break;
default :
}
if (queryResultPath.compareEndingPath("value")) {
List<Object> value=queryResultPath.getLastObject();
value.add(o);
}
amount++;
}
}
