protected void initView(){
  mRxTitle.setLeftFinish(mContext);
  mSeatView.setScreenName("3????");
  mSeatView.setMaxSelected(8);
  mSeatView.setSeatChecker(new RxSeatMovie.SeatChecker(){
    @Override public boolean isValidSeat(    int row,    int column){
      return !(column == 2 || column == 12);
    }
    @Override public boolean isSold(    int row,    int column){
      return row == 6 && column == 6;
    }
    @Override public void checked(    int row,    int column){
    }
    @Override public void unCheck(    int row,    int column){
    }
    @Override public String[] checkedSeatTxt(    int row,    int column){
      return null;
    }
  }
);
  mSeatView.setData(10,15);
}
