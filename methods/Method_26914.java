private void getTargetIds(@NonNull XmlPullParser parser,@NonNull AttributeSet attrs,@NonNull Transition transition) throws XmlPullParserException, IOException {
  int type;
  int depth=parser.getDepth();
  while (((type=parser.next()) != XmlPullParser.END_TAG || parser.getDepth() > depth) && type != XmlPullParser.END_DOCUMENT) {
    if (type != XmlPullParser.START_TAG) {
      continue;
    }
    String name=parser.getName();
    if (name.equals("target")) {
      TypedArray a=mContext.obtainStyledAttributes(attrs,R.styleable.TransitionTarget);
      int id=a.getResourceId(R.styleable.TransitionTarget_targetId,0);
      String transitionName;
      if (id != 0) {
        transition.addTarget(id);
      }
 else       if ((id=a.getResourceId(R.styleable.TransitionTarget_excludeId,0)) != 0) {
        transition.excludeTarget(id,true);
      }
 else       if ((transitionName=a.getString(R.styleable.TransitionTarget_targetName)) != null) {
        transition.addTarget(transitionName);
      }
 else       if ((transitionName=a.getString(R.styleable.TransitionTarget_excludeName)) != null) {
        transition.excludeTarget(transitionName,true);
      }
 else {
        String className=a.getString(R.styleable.TransitionTarget_excludeClass);
        try {
          if (className != null) {
            Class clazz=Class.forName(className);
            transition.excludeTarget(clazz,true);
          }
 else           if ((className=a.getString(R.styleable.TransitionTarget_targetClass)) != null) {
            Class clazz=Class.forName(className);
            transition.addTarget(clazz);
          }
        }
 catch (        ClassNotFoundException e) {
          throw new RuntimeException("Could not create " + className,e);
        }
      }
      a.recycle();
    }
 else {
      throw new RuntimeException("Unknown scene name: " + parser.getName());
    }
  }
}
