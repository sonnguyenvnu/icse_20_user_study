@Override protected Set<String> responseParams(){
  Set<String> responseParams=new HashSet<>(super.responseParams());
  responseParams.addAll(Arrays.asList("sql","flat","separator","_score","_type","_id","newLine","format"));
  return responseParams;
}
