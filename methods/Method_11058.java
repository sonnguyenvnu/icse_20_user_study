public static void initDialogShowPicture(Context mContext,final List<ModelPicture> modelList){
  final RxDialog mDialogShowPicture=new RxDialog(mContext);
  View dialogView=LayoutInflater.from(mContext).inflate(R.layout.dialog_show_picture,null);
  final RxCardStackView mStackView=dialogView.findViewById(R.id.stackview_main);
  final LinearLayout mButtonContainer=dialogView.findViewById(R.id.button_container);
  Button btnPre=dialogView.findViewById(R.id.btn_Pre);
  Button btnNext=dialogView.findViewById(R.id.btn_next);
  mStackView.setItemExpendListener(new RxCardStackView.ItemExpendListener(){
    @Override public void onItemExpend(    boolean expend){
      mButtonContainer.setVisibility(expend ? View.VISIBLE : View.INVISIBLE);
    }
  }
);
  AdapterCardViewModelPicture testStackAdapter=new AdapterCardViewModelPicture(mContext);
  mStackView.setAdapter(testStackAdapter);
  testStackAdapter.updateData(modelList);
  mDialogShowPicture.setContentView(dialogView);
  mDialogShowPicture.setFullScreen();
  if (modelList.size() > 1) {
    btnPre.setOnClickListener(new View.OnClickListener(){
      @Override public void onClick(      View v){
        if (mStackView.getSelectPosition() == 0) {
          RxToast.info("??????");
        }
 else {
          mStackView.pre();
        }
      }
    }
);
    btnNext.setOnClickListener(new View.OnClickListener(){
      @Override public void onClick(      View v){
        if (mStackView.getSelectPosition() == modelList.size() - 1) {
          RxToast.info("???????");
        }
 else {
          mStackView.next();
        }
      }
    }
);
    btnPre.setText("???");
    btnNext.setVisibility(View.VISIBLE);
    btnPre.setVisibility(View.VISIBLE);
  }
 else {
    btnPre.setText("??");
    btnPre.setVisibility(View.VISIBLE);
    btnPre.setOnClickListener(new View.OnClickListener(){
      @Override public void onClick(      View v){
        mDialogShowPicture.cancel();
      }
    }
);
    btnNext.setVisibility(View.GONE);
  }
  testStackAdapter.updateData(modelList);
  mDialogShowPicture.show();
}
