private void initUI(){
  mFocusLayerView=(FrameLayout)findViewById(R.id.focusLayer);
  mSearchET=(AutoCompleteEditor)findViewById(R.id.searchET);
  mSearchET.addTextChangedListener(this);
  mSuggestionsAdapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,new ArrayList<String>());
  mSearchET.setAdapter(mSuggestionsAdapter);
  mSearchET.setOnItemClickListener(new OnItemClickListener(){
    @Override public void onItemClick(    AdapterView<?> parent,    View view,    int position,    long id){
      InputMethodManager imm=(InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(mFocusLayerView.getWindowToken(),0);
      mSearchET.clearFocus();
      mListener.searchCode(getSearchString());
    }
  }
);
  mCancelButton=(ImageView)findViewById(R.id.cancel);
  mCancelButton.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      mSearchET.setText("");
      MainActivity.getMainPresenter().getMapActionsListener().startUpdateCodeOnDrag();
    }
  }
);
  mSearchET.setImeBackListener(this);
  mSearchET.setOnFocusChangeListener(new OnFocusChangeListener(){
    @Override public void onFocusChange(    View v,    boolean hasFocus){
      mEditFieldFocused=hasFocus;
    }
  }
);
  mSearchET.setOnEditorActionListener(new OnEditorActionListener(){
    @Override public boolean onEditorAction(    TextView v,    int actionId,    KeyEvent event){
      if (actionId == EditorInfo.IME_ACTION_SEARCH) {
        InputMethodManager imm=(InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mFocusLayerView.getWindowToken(),0);
        mSearchET.clearFocus();
        mListener.searchCode(getSearchString());
        return true;
      }
      return false;
    }
  }
);
  if (VERSION.SDK_INT >= 17) {
    mSearchET.setOnDismissListener(new OnDismissListener(){
      @Override public void onDismiss(){
        mFocusLayerView.setAlpha(0f);
      }
    }
);
  }
  mFocusLayerView.setOnTouchListener(new OnTouchListener(){
    @Override public boolean onTouch(    View v,    MotionEvent event){
      if (mEditFieldFocused) {
        InputMethodManager imm=(InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mFocusLayerView.getWindowToken(),0);
        mSearchET.clearFocus();
      }
      return false;
    }
  }
);
}
