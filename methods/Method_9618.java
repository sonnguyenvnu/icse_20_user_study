private boolean processTouchEvent(MotionEvent ev){
  if (photoAnimationInProgress != 0 || animationStartTime != 0) {
    return false;
  }
  if (ev.getPointerCount() == 1 && gestureDetector.onTouchEvent(ev) && doubleTap) {
    doubleTap=false;
    moving=false;
    zooming=false;
    checkMinMax(false);
    return true;
  }
  if (ev.getActionMasked() == MotionEvent.ACTION_DOWN || ev.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
    discardTap=false;
    if (!scroller.isFinished()) {
      scroller.abortAnimation();
    }
    if (!draggingDown) {
      if (ev.getPointerCount() == 2) {
        pinchStartDistance=(float)Math.hypot(ev.getX(1) - ev.getX(0),ev.getY(1) - ev.getY(0));
        pinchStartScale=scale;
        pinchCenterX=(ev.getX(0) + ev.getX(1)) / 2.0f;
        pinchCenterY=(ev.getY(0) + ev.getY(1)) / 2.0f;
        pinchStartX=translationX;
        pinchStartY=translationY;
        zooming=true;
        moving=false;
        if (velocityTracker != null) {
          velocityTracker.clear();
        }
      }
 else       if (ev.getPointerCount() == 1) {
        moveStartX=ev.getX();
        dragY=moveStartY=ev.getY();
        draggingDown=false;
        canDragDown=true;
        if (velocityTracker != null) {
          velocityTracker.clear();
        }
      }
    }
  }
 else   if (ev.getActionMasked() == MotionEvent.ACTION_MOVE) {
    if (ev.getPointerCount() == 2 && !draggingDown && zooming) {
      discardTap=true;
      scale=(float)Math.hypot(ev.getX(1) - ev.getX(0),ev.getY(1) - ev.getY(0)) / pinchStartDistance * pinchStartScale;
      translationX=(pinchCenterX - getContainerViewWidth() / 2) - ((pinchCenterX - getContainerViewWidth() / 2) - pinchStartX) * (scale / pinchStartScale);
      translationY=(pinchCenterY - getContainerViewHeight() / 2) - ((pinchCenterY - getContainerViewHeight() / 2) - pinchStartY) * (scale / pinchStartScale);
      updateMinMax(scale);
      containerView.invalidate();
    }
 else     if (ev.getPointerCount() == 1) {
      if (velocityTracker != null) {
        velocityTracker.addMovement(ev);
      }
      float dx=Math.abs(ev.getX() - moveStartX);
      float dy=Math.abs(ev.getY() - dragY);
      if (dx > AndroidUtilities.dp(3) || dy > AndroidUtilities.dp(3)) {
        discardTap=true;
      }
      if (canDragDown && !draggingDown && scale == 1 && dy >= AndroidUtilities.dp(30) && dy / 2 > dx) {
        draggingDown=true;
        moving=false;
        dragY=ev.getY();
        if (isActionBarVisible) {
          toggleActionBar(false,true);
        }
        return true;
      }
 else       if (draggingDown) {
        translationY=ev.getY() - dragY;
        containerView.invalidate();
      }
 else       if (!invalidCoords && animationStartTime == 0) {
        float moveDx=moveStartX - ev.getX();
        float moveDy=moveStartY - ev.getY();
        if (moving || scale == 1 && Math.abs(moveDy) + AndroidUtilities.dp(12) < Math.abs(moveDx) || scale != 1) {
          if (!moving) {
            moveDx=0;
            moveDy=0;
            moving=true;
            canDragDown=false;
          }
          moveStartX=ev.getX();
          moveStartY=ev.getY();
          updateMinMax(scale);
          if (translationX < minX || translationX > maxX) {
            moveDx/=3.0f;
          }
          if (maxY == 0 && minY == 0) {
            if (translationY - moveDy < minY) {
              translationY=minY;
              moveDy=0;
            }
 else             if (translationY - moveDy > maxY) {
              translationY=maxY;
              moveDy=0;
            }
          }
 else {
            if (translationY < minY || translationY > maxY) {
              moveDy/=3.0f;
            }
          }
          translationX-=moveDx;
          if (scale != 1) {
            translationY-=moveDy;
          }
          containerView.invalidate();
        }
      }
 else {
        invalidCoords=false;
        moveStartX=ev.getX();
        moveStartY=ev.getY();
      }
    }
  }
 else   if (ev.getActionMasked() == MotionEvent.ACTION_CANCEL || ev.getActionMasked() == MotionEvent.ACTION_UP || ev.getActionMasked() == MotionEvent.ACTION_POINTER_UP) {
    if (zooming) {
      invalidCoords=true;
      if (scale < 1.0f) {
        updateMinMax(1.0f);
        animateTo(1.0f,0,0,true);
      }
 else       if (scale > 3.0f) {
        float atx=(pinchCenterX - getContainerViewWidth() / 2) - ((pinchCenterX - getContainerViewWidth() / 2) - pinchStartX) * (3.0f / pinchStartScale);
        float aty=(pinchCenterY - getContainerViewHeight() / 2) - ((pinchCenterY - getContainerViewHeight() / 2) - pinchStartY) * (3.0f / pinchStartScale);
        updateMinMax(3.0f);
        if (atx < minX) {
          atx=minX;
        }
 else         if (atx > maxX) {
          atx=maxX;
        }
        if (aty < minY) {
          aty=minY;
        }
 else         if (aty > maxY) {
          aty=maxY;
        }
        animateTo(3.0f,atx,aty,true);
      }
 else {
        checkMinMax(true);
      }
      zooming=false;
    }
 else     if (draggingDown) {
      if (Math.abs(dragY - ev.getY()) > getContainerViewHeight() / 6.0f) {
        closePhoto(true,false);
      }
 else {
        animateTo(1,0,0,false);
      }
      draggingDown=false;
    }
 else     if (moving) {
      float moveToX=translationX;
      float moveToY=translationY;
      updateMinMax(scale);
      moving=false;
      canDragDown=true;
      if (velocityTracker != null && scale == 1) {
        velocityTracker.computeCurrentVelocity(1000);
      }
      if (translationX < minX) {
        moveToX=minX;
      }
 else       if (translationX > maxX) {
        moveToX=maxX;
      }
      if (translationY < minY) {
        moveToY=minY;
      }
 else       if (translationY > maxY) {
        moveToY=maxY;
      }
      animateTo(scale,moveToX,moveToY,false);
    }
  }
  return false;
}
