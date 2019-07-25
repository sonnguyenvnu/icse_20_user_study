@Override public Endpoint decode(String segment){
  final String[] tokens=segment.split(fieldDelimiter);
  final Endpoint endpoint;
switch (tokens.length) {
case 1:
    endpoint=Endpoint.of(segment);
  break;
case 2:
{
  final String host=tokens[0];
  final int port=Integer.parseInt(tokens[1]);
  if (port == 0) {
    endpoint=Endpoint.of(host);
  }
 else {
    endpoint=Endpoint.of(host,port);
  }
  break;
}
case 3:
{
final String host=tokens[0];
final int port=Integer.parseInt(tokens[1]);
final int weight=Integer.parseInt(tokens[2]);
if (port == 0) {
  endpoint=Endpoint.of(host).withWeight(weight);
}
 else {
  endpoint=Endpoint.of(host,port).withWeight(weight);
}
break;
}
default :
throw new EndpointGroupException("invalid endpoint list: " + segment);
}
return endpoint;
}
