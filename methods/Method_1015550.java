public void leave(Address mbr){
  if (sendLeaveReqToCoord(gms.determineCoordinator()))   gms.initState();
}
