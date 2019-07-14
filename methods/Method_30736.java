@NonNull private View createViewFromResource(@NonNull LayoutInflater inflater,int position,@Nullable View convertView,@NonNull ViewGroup parent,int resource){
  final View view;
  final TextView text;
  if (convertView == null) {
    view=inflater.inflate(resource,parent,false);
  }
 else {
    view=convertView;
  }
  try {
    if (mFieldId == 0) {
      text=(TextView)view;
    }
 else {
      text=view.findViewById(mFieldId);
      if (text == null) {
        throw new RuntimeException("Failed to find view with ID " + getContext().getResources().getResourceName(mFieldId) + " in item layout");
      }
    }
  }
 catch (  ClassCastException e) {
    Log.e("ArrayAdapter","You must supply a resource ID for a TextView");
    throw new IllegalStateException("ArrayAdapter requires the resource ID to be a TextView",e);
  }
  final T item=getItem(position);
  if (item instanceof CharSequence) {
    text.setText((CharSequence)item);
  }
 else {
    text.setText(item.toString());
  }
  return view;
}
