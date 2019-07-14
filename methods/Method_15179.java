@Override public void initEvent(){
  searchHandler=new Handler(new Callback(){
    @Override public boolean handleMessage(    Message msg){
      if (msg == null) {
        Log.e(TAG,"searchHandler  handleMessage  (msg == null >> return false;");
        return false;
      }
      Log.i(TAG,"inputedString = " + inputedString + "msg.obj = " + msg.obj);
      if (inputedString != null) {
        if (inputedString.equals(msg.obj)) {
          getList(intentType);
        }
      }
      return false;
    }
  }
);
  etEditTextInfo.addTextChangedListener(new TextWatcher(){
    @Override public void onTextChanged(    CharSequence s,    int start,    int before,    int count){
      inputedString=StringUtil.getTrimedString(s);
      if (StringUtil.isNotEmpty(inputedString,true) == false) {
        ivEditTextInfoClear.setVisibility(View.GONE);
      }
 else {
        ivEditTextInfoClear.setVisibility(View.VISIBLE);
        if (hasUrl == true) {
          Message msg=new Message();
          msg.obj=inputedString;
          searchHandler.sendMessageDelayed(msg,SEARCH_DELAY_TIME);
        }
      }
    }
    @Override public void beforeTextChanged(    CharSequence s,    int start,    int count,    int after){
    }
    @Override public void afterTextChanged(    Editable s){
    }
  }
);
  ivEditTextInfoClear.setOnClickListener(new OnClickListener(){
    @Override public void onClick(    View v){
      etEditTextInfo.setText("");
    }
  }
);
  etEditTextInfo.setText(StringUtil.getTrimedString(getIntent().getStringExtra(INTENT_VALUE)));
  etEditTextInfo.setSelection(StringUtil.getLength(etEditTextInfo,true));
  if (hasList == true) {
    EditTextUtil.hideKeyboard(context,etEditTextInfo);
    if (hasUrl) {
      lvEditTextInfo.setOnScrollListener(new OnScrollListener(){
        @Override public void onScrollStateChanged(        AbsListView view,        int scrollState){
        }
        @Override public void onScroll(        AbsListView view,        int firstVisibleItem,        int visibleItemCount,        int totalItemCount){
          if (adapter != null && lvEditTextInfo.getLastVisiblePosition() >= adapter.getCount() - 1) {
            requestSize+=20;
            Log.i(TAG,"initEvent  lvEditTextInfo.setOnScrollListener( >> onScroll getList(intentType);requestSize = " + requestSize);
            getList(intentType);
          }
        }
      }
);
    }
    lvEditTextInfo.setOnItemClickListener(new OnItemClickListener(){
      @Override public void onItemClick(      AdapterView<?> parent,      View view,      int position,      long id){
        if (position < adapter.getCount()) {
          etEditTextInfo.setText("" + adapter.getItem(position));
          if (hasUrl) {
            intent=new Intent();
            intent.putExtra(RESULT_TYPE,getIntent().getIntExtra(INTENT_TYPE,-1));
            intent.putExtra(RESULT_KEY,getIntent().getStringExtra(INTENT_KEY));
            intent.putExtra(RESULT_VALUE,adapter.getItem(position));
            setResult(RESULT_OK,intent);
            finish();
            return;
          }
          saveAndExit();
        }
      }
    }
);
    lvEditTextInfo.setOnTouchListener(new View.OnTouchListener(){
      @SuppressLint("ClickableViewAccessibility") @Override public boolean onTouch(      View v,      MotionEvent event){
        EditTextUtil.hideKeyboard(context,etEditTextInfo);
        return false;
      }
    }
);
  }
}
