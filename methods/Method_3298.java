/** 
 * ??????
 */
public void forwardbackward(){
  if (!x_.isEmpty()) {
    for (int i=0; i < x_.size(); i++) {
      for (int j=0; j < ysize_; j++) {
        node_.get(i).get(j).calcAlpha();
      }
    }
    for (int i=x_.size() - 1; i >= 0; i--) {
      for (int j=0; j < ysize_; j++) {
        node_.get(i).get(j).calcBeta();
      }
    }
    Z_=0.0;
    for (int j=0; j < ysize_; j++) {
      Z_=Node.logsumexp(Z_,node_.get(0).get(j).beta,j == 0);
    }
  }
}
