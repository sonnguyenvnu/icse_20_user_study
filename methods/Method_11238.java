public void updateViewData(){
  long tempSum=TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes();
  long rxtxLast=tempSum - rxtxTotal;
  double totalSpeed=rxtxLast * 1000 / timeSpan;
  rxtxTotal=tempSum;
  long tempMobileRx=TrafficStats.getMobileRxBytes();
  long tempMobileTx=TrafficStats.getMobileTxBytes();
  long tempWlanRx=TrafficStats.getTotalRxBytes() - tempMobileRx;
  long tempWlanTx=TrafficStats.getTotalTxBytes() - tempMobileTx;
  long mobileLastRecv=tempMobileRx - mobileRecvSum;
  long mobileLastSend=tempMobileTx - mobileSendSum;
  long wlanLastRecv=tempWlanRx - wlanRecvSum;
  long wlanLastSend=tempWlanTx - wlanSendSum;
  double mobileRecvSpeed=mobileLastRecv * 1000 / timeSpan;
  double mobileSendSpeed=mobileLastSend * 1000 / timeSpan;
  double wlanRecvSpeed=wlanLastRecv * 1000 / timeSpan;
  double wlanSendSpeed=wlanLastSend * 1000 / timeSpan;
  mobileRecvSum=tempMobileRx;
  mobileSendSum=tempMobileTx;
  wlanRecvSum=tempWlanRx;
  wlanSendSum=tempWlanTx;
  if (isMulti) {
    if (mobileRecvSpeed >= 0d) {
      tvMobileRx.setText(showSpeed(mobileRecvSpeed));
    }
    if (mobileSendSpeed >= 0d) {
      tvMobileTx.setText(showSpeed(mobileSendSpeed));
    }
    if (wlanRecvSpeed >= 0d) {
      tvWlanRx.setText(showSpeed(wlanRecvSpeed));
    }
    if (wlanSendSpeed >= 0d) {
      tvWlanTx.setText(showSpeed(wlanSendSpeed));
    }
  }
 else {
    if (totalSpeed >= 0d) {
      tvSum.setText(showSpeed(totalSpeed));
    }
  }
}
