public void chooseColor(@StringRes int title,final ColorChooser chooser,int defaultColor){
  final AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(getActivity(),getActivity().getDialogStyle());
  View dialogLayout=LayoutInflater.from(getActivity()).inflate(R.layout.dialog_color_picker,null);
  final LineColorPicker colorPicker=(LineColorPicker)dialogLayout.findViewById(R.id.color_picker_primary);
  final LineColorPicker colorPicker2=(LineColorPicker)dialogLayout.findViewById(R.id.color_picker_primary_2);
  final TextView dialogTitle=(TextView)dialogLayout.findViewById(R.id.dialog_title);
  dialogTitle.setText(title);
  ((CardView)dialogLayout.findViewById(R.id.dialog_card)).setCardBackgroundColor(getActivity().getCardBackgroundColor());
  colorPicker2.setOnColorChangedListener(new OnColorChangedListener(){
    @Override public void onColorChanged(    int c){
      dialogTitle.setBackgroundColor(c);
      chooser.onColorChanged(c);
    }
  }
);
  colorPicker.setOnColorChangedListener(new OnColorChangedListener(){
    @Override public void onColorChanged(    int c){
      colorPicker2.setColors(ColorPalette.getColors(getActivity(),colorPicker.getColor()));
      colorPicker2.setSelectedColor(colorPicker.getColor());
    }
  }
);
  int[] baseColors=ColorPalette.getBaseColors(getActivity());
  colorPicker.setColors(baseColors);
  for (  int i : baseColors) {
    for (    int i2 : ColorPalette.getColors(getActivity(),i))     if (i2 == defaultColor) {
      colorPicker.setSelectedColor(i);
      colorPicker2.setColors(ColorPalette.getColors(getActivity(),i));
      colorPicker2.setSelectedColor(i2);
      break;
    }
  }
  dialogBuilder.setView(dialogLayout);
  dialogBuilder.setNegativeButton(getActivity().getString(R.string.cancel).toUpperCase(),new DialogInterface.OnClickListener(){
    @Override public void onClick(    DialogInterface dialog,    int which){
      dialog.cancel();
      chooser.onDialogDismiss();
    }
  }
);
  dialogBuilder.setPositiveButton(getActivity().getString(R.string.ok_action).toUpperCase(),new DialogInterface.OnClickListener(){
    public void onClick(    DialogInterface dialog,    int which){
      AlertDialog alertDialog=(AlertDialog)dialog;
      alertDialog.setOnDismissListener(null);
      chooser.onColorSelected(colorPicker2.getColor());
    }
  }
);
  dialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener(){
    @Override public void onDismiss(    DialogInterface dialog){
      chooser.onDialogDismiss();
    }
  }
);
  dialogBuilder.show();
}
