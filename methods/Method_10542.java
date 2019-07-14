@OnClick({R.id.btn_null,R.id.btn_null_obj,R.id.btn_is_integer,R.id.btn_is_double,R.id.btn_is_number,R.id.btn_astro,R.id.btn_hind_mobile,R.id.btn_format_bank_card,R.id.btn_format_bank_card_4,R.id.btn_getAmountValue,R.id.btn_getRoundUp,R.id.btn_string_to_int,R.id.btn_string_to_long,R.id.btn_string_to_double,R.id.btn_string_to_float,R.id.btn_string_to_two_number,R.id.btn_upperFirstLetter,R.id.btn_lowerFirstLetter,R.id.btn_reverse,R.id.btn_toDBC,R.id.btn_toSBC,R.id.btn_oneCn2ASCII,R.id.btn_oneCn2PY,R.id.btn_getPYFirstLetter,R.id.btn_getPYAllFirstLetter,R.id.btn_cn2PY}) public void onViewClicked(View view){
switch (view.getId()) {
case R.id.btn_null:
    mTvNull.setText(RxDataTool.isNullString(mEditText.getText().toString()) + "");
  break;
case R.id.btn_null_obj:
mTvNullObj.setText(RxDataTool.isNullString(mEditText.getText().toString()) + "");
break;
case R.id.btn_is_integer:
mTvIsInteger.setText(RxDataTool.isInteger(mEditText.getText().toString()) + "");
break;
case R.id.btn_is_double:
mTvIsDouble.setText(RxDataTool.isDouble(mEditText.getText().toString()) + "");
break;
case R.id.btn_is_number:
mTvIsNumber.setText(RxDataTool.isNumber(mEditText.getText().toString()) + "");
break;
case R.id.btn_astro:
mTvAstro.setText(RxDataTool.getAstro(RxDataTool.stringToInt(mEdMonth.getText().toString()),RxDataTool.stringToInt(mEdDay.getText().toString())));
break;
case R.id.btn_hind_mobile:
mTvHindMobile.setText(RxDataTool.hideMobilePhone4(mEdMobile.getText().toString()));
break;
case R.id.btn_format_bank_card:
mTvFormatBankCard.setText(RxDataTool.formatCard(mEdBankCard.getText().toString()));
break;
case R.id.btn_format_bank_card_4:
mTvFormatBankCard4.setText(RxDataTool.formatCardEnd4(mEdBankCard.getText().toString()));
break;
case R.id.btn_getAmountValue:
mTvGetAmountValue.setText(RxDataTool.getAmountValue(mEdMoney.getText().toString()));
break;
case R.id.btn_getRoundUp:
mTvGetRoundUp.setText(RxDataTool.getRoundUp(mEdMoney.getText().toString(),2));
break;
case R.id.btn_string_to_int:
mTvStringToInt.setText(RxDataTool.stringToInt(mEdText.getText().toString()) + "");
break;
case R.id.btn_string_to_long:
mTvStringToLong.setText(RxDataTool.stringToLong(mEdText.getText().toString()) + "");
break;
case R.id.btn_string_to_double:
mTvStringToDouble.setText(RxDataTool.stringToDouble(mEdText.getText().toString()) + "");
break;
case R.id.btn_string_to_float:
mTvStringToFloat.setText(RxDataTool.stringToFloat(mEdText.getText().toString()) + "");
break;
case R.id.btn_string_to_two_number:
mTvStringToTwoNumber.setText(RxDataTool.format2Decimals(mEdText.getText().toString()) + "");
break;
case R.id.btn_upperFirstLetter:
mTvUpperFirstLetter.setText(RxDataTool.upperFirstLetter(mEdString.getText().toString()));
break;
case R.id.btn_lowerFirstLetter:
mTvLowerFirstLetter.setText(RxDataTool.lowerFirstLetter(mEdString.getText().toString()));
break;
case R.id.btn_reverse:
mTvReverse.setText(RxDataTool.reverse(mEdString.getText().toString()));
break;
case R.id.btn_toDBC:
mTvToDBC.setText(RxDataTool.toDBC(mEdString.getText().toString()));
break;
case R.id.btn_toSBC:
mTvToSBC.setText(RxDataTool.toSBC(mEdString.getText().toString()));
break;
case R.id.btn_oneCn2ASCII:
mTvOneCn2ASCII.setText(RxDataTool.oneCn2ASCII(mEdString.getText().toString()) + "");
break;
case R.id.btn_oneCn2PY:
mTvOneCn2PY.setText(RxDataTool.oneCn2PY(mEdString.getText().toString()));
break;
case R.id.btn_getPYFirstLetter:
mTvGetPYFirstLetter.setText(RxDataTool.getPYFirstLetter(mEdString.getText().toString()));
break;
case R.id.btn_getPYAllFirstLetter:
mTvGetPYAllFirstLetter.setText(RxDataTool.getPYAllFirstLetter(mEdString.getText().toString()));
break;
case R.id.btn_cn2PY:
mTvCn2PY.setText(RxDataTool.cn2PY(mEdString.getText().toString()));
break;
}
}
