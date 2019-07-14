private void initEvent(){
  mRxTitle.setLeftIconVisibility(true);
  mRxTitle.setTitleColor(Color.WHITE);
  mRxTitle.setTitleSize(RxImageTool.dp2px(20));
  mRxTitle.setLeftFinish(mContext);
  mRxTickerViewScan.setAnimationDuration(500);
  mIvCreateQrCode.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      String str=mEtQrCode.getText().toString();
      if (RxDataTool.isNullString(str)) {
        RxToast.error("????????????");
      }
 else {
        mLlCode.setVisibility(View.VISIBLE);
        RxQRCode.builder(str).backColor(0xFFFFFFFF).codeColor(0xFF000000).codeSide(600).into(mIvQrCode);
        mIvQrCode.setVisibility(View.VISIBLE);
        RxToast.success("??????!");
        RxSPTool.putContent(mContext,SP_MADE_CODE,RxDataTool.stringToInt(RxSPTool.getContent(mContext,SP_MADE_CODE)) + 1 + "");
        updateMadeCodeCount();
        nestedScrollView.computeScroll();
      }
    }
  }
);
  mIvCreateBarCode.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      String str1=mEtBarCode.getText().toString();
      if (RxDataTool.isNullString(str1)) {
        RxToast.error("????????????");
      }
 else {
        mLlBarCode.setVisibility(View.VISIBLE);
        RxBarCode.builder(str1).backColor(0x00000000).codeColor(0xFF000000).codeWidth(1000).codeHeight(300).into(mIvBarCode);
        mIvBarCode.setVisibility(View.VISIBLE);
        RxToast.success("??????!");
        RxSPTool.putContent(mContext,SP_MADE_CODE,RxDataTool.stringToInt(RxSPTool.getContent(mContext,SP_MADE_CODE)) + 1 + "");
        updateMadeCodeCount();
      }
    }
  }
);
  mLlScaner.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      RxActivityTool.skipActivity(mContext,ActivityScanerCode.class);
    }
  }
);
  mLlQr.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      mLlQrRoot.setVisibility(View.VISIBLE);
      mLlBarRoot.setVisibility(View.GONE);
    }
  }
);
  mLlBar.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      mLlBarRoot.setVisibility(View.VISIBLE);
      mLlQrRoot.setVisibility(View.GONE);
    }
  }
);
}
