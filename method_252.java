/** 
 * return true if it is certain that expected attributes don't occur during startTime and endTime, else return false.
 */
public void _XXXXX_(List<Object> appearAttrs,long occurTime){
  if (expired) {
    throw new IllegalStateException("Expired window can't recieve events");
  }
switch (status) {
case not_sure:
    if (occurTime < window.startTime) {
      break;
    }
 else     if (occurTime >= window.startTime && occurTime <= window.endTime) {
      if (expectAttrs.equals(appearAttrs)) {
        status=OccurStatus.occured;
      }
      break;
    }
 else {
      status=OccurStatus.absent;
      break;
    }
case occured:
  if (occurTime > window.endTime) {
    expired=true;
  }
break;
default :
break;
}
if (status == OccurStatus.absent) {
expired=true;
}
}