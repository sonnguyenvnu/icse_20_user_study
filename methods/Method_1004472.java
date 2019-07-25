/** 
 * ???????????????????????????????1 - 100??? ??20,21,50,73?4???????????? [1, 19], [22, 49], [51, 72], [74, 100] ???????
 */
private List<GetMessageResult> filter(PullRequest request,GetMessageResult input){
  List<GetMessageResult> result=new ArrayList<>();
  List<Buffer> messages=input.getBuffers();
  OffsetRange offsetRange=input.getConsumerLogRange();
  GetMessageResult range=null;
  long begin=-1;
  long end=-1;
  for (int i=0; i < messages.size(); ++i) {
    Buffer message=messages.get(i);
    if (pullResultFilter.needKeep(request,message)) {
      if (range == null) {
        range=new GetMessageResult();
        result.add(range);
        begin=offsetRange.getBegin() + i;
      }
      end=offsetRange.getBegin() + i;
      range.addBuffer(message);
    }
 else {
      message.release();
      setOffsetRange(range,begin,end);
      range=null;
    }
  }
  setOffsetRange(range,begin,end);
  appendEmpty(end,offsetRange,result);
  return result;
}
