private void drawDrawable(Canvas canvas,Drawable drawable,int alpha,BitmapShader shader,int orientation){
  if (drawable instanceof BitmapDrawable) {
    BitmapDrawable bitmapDrawable=(BitmapDrawable)drawable;
    Paint paint;
    if (shader != null) {
      paint=roundPaint;
    }
 else {
      paint=bitmapDrawable.getPaint();
    }
    boolean hasFilter=paint != null && paint.getColorFilter() != null;
    if (hasFilter && isPressed == 0) {
      if (shader != null) {
        roundPaint.setColorFilter(null);
      }
 else       if (staticThumbDrawable != drawable) {
        bitmapDrawable.setColorFilter(null);
      }
    }
 else     if (!hasFilter && isPressed != 0) {
      if (isPressed == 1) {
        if (shader != null) {
          roundPaint.setColorFilter(selectedColorFilter);
        }
 else {
          bitmapDrawable.setColorFilter(selectedColorFilter);
        }
      }
 else {
        if (shader != null) {
          roundPaint.setColorFilter(selectedGroupColorFilter);
        }
 else {
          bitmapDrawable.setColorFilter(selectedGroupColorFilter);
        }
      }
    }
    if (colorFilter != null) {
      if (shader != null) {
        roundPaint.setColorFilter(colorFilter);
      }
 else {
        bitmapDrawable.setColorFilter(colorFilter);
      }
    }
    int bitmapW;
    int bitmapH;
    if (bitmapDrawable instanceof AnimatedFileDrawable) {
      if (orientation % 360 == 90 || orientation % 360 == 270) {
        bitmapW=bitmapDrawable.getIntrinsicHeight();
        bitmapH=bitmapDrawable.getIntrinsicWidth();
      }
 else {
        bitmapW=bitmapDrawable.getIntrinsicWidth();
        bitmapH=bitmapDrawable.getIntrinsicHeight();
      }
    }
 else {
      if (orientation % 360 == 90 || orientation % 360 == 270) {
        bitmapW=bitmapDrawable.getBitmap().getHeight();
        bitmapH=bitmapDrawable.getBitmap().getWidth();
      }
 else {
        bitmapW=bitmapDrawable.getBitmap().getWidth();
        bitmapH=bitmapDrawable.getBitmap().getHeight();
      }
    }
    float realImageW=imageW - sideClip * 2;
    float realImageH=imageH - sideClip * 2;
    float scaleW=imageW == 0 ? 1.0f : (bitmapW / realImageW);
    float scaleH=imageH == 0 ? 1.0f : (bitmapH / realImageH);
    if (shader != null) {
      if (isAspectFit) {
        float scale=Math.max(scaleW,scaleH);
        bitmapW/=scale;
        bitmapH/=scale;
        drawRegion.set(imageX + (imageW - bitmapW) / 2,imageY + (imageH - bitmapH) / 2,imageX + (imageW + bitmapW) / 2,imageY + (imageH + bitmapH) / 2);
        if (isVisible) {
          roundPaint.setShader(shader);
          shaderMatrix.reset();
          shaderMatrix.setTranslate(drawRegion.left,drawRegion.top);
          shaderMatrix.preScale(1.0f / scale,1.0f / scale);
          shader.setLocalMatrix(shaderMatrix);
          roundPaint.setAlpha(alpha);
          roundRect.set(drawRegion);
          canvas.drawRoundRect(roundRect,roundRadius,roundRadius,roundPaint);
        }
      }
 else {
        roundPaint.setShader(shader);
        float scale=1.0f / Math.min(scaleW,scaleH);
        roundRect.set(imageX + sideClip,imageY + sideClip,imageX + imageW - sideClip,imageY + imageH - sideClip);
        shaderMatrix.reset();
        if (Math.abs(scaleW - scaleH) > 0.0005f) {
          if (bitmapW / scaleH > realImageW) {
            bitmapW/=scaleH;
            drawRegion.set(imageX - (bitmapW - realImageW) / 2,imageY,imageX + (bitmapW + realImageW) / 2,imageY + realImageH);
          }
 else {
            bitmapH/=scaleW;
            drawRegion.set(imageX,imageY - (bitmapH - realImageH) / 2,imageX + realImageW,imageY + (bitmapH + realImageH) / 2);
          }
        }
 else {
          drawRegion.set(imageX,imageY,imageX + realImageW,imageY + realImageH);
        }
        if (isVisible) {
          shaderMatrix.reset();
          shaderMatrix.setTranslate(drawRegion.left + sideClip,drawRegion.top + sideClip);
          if (orientation == 90) {
            shaderMatrix.preRotate(90);
            shaderMatrix.preTranslate(0,-drawRegion.width());
          }
 else           if (orientation == 180) {
            shaderMatrix.preRotate(180);
            shaderMatrix.preTranslate(-drawRegion.width(),-drawRegion.height());
          }
 else           if (orientation == 270) {
            shaderMatrix.preRotate(270);
            shaderMatrix.preTranslate(-drawRegion.height(),0);
          }
          shaderMatrix.preScale(scale,scale);
          shader.setLocalMatrix(shaderMatrix);
          roundPaint.setAlpha(alpha);
          canvas.drawRoundRect(roundRect,roundRadius,roundRadius,roundPaint);
        }
      }
    }
 else {
      if (isAspectFit) {
        float scale=Math.max(scaleW,scaleH);
        canvas.save();
        bitmapW/=scale;
        bitmapH/=scale;
        drawRegion.set(imageX + (imageW - bitmapW) / 2.0f,imageY + (imageH - bitmapH) / 2.0f,imageX + (imageW + bitmapW) / 2.0f,imageY + (imageH + bitmapH) / 2.0f);
        bitmapDrawable.setBounds((int)drawRegion.left,(int)drawRegion.top,(int)drawRegion.right,(int)drawRegion.bottom);
        if (bitmapDrawable instanceof AnimatedFileDrawable) {
          ((AnimatedFileDrawable)bitmapDrawable).setActualDrawRect(drawRegion.left,drawRegion.top,drawRegion.width(),drawRegion.height());
        }
        if (isVisible) {
          try {
            bitmapDrawable.setAlpha(alpha);
            bitmapDrawable.draw(canvas);
          }
 catch (          Exception e) {
            onBitmapException(bitmapDrawable);
            FileLog.e(e);
          }
        }
        canvas.restore();
      }
 else {
        if (Math.abs(scaleW - scaleH) > 0.00001f) {
          canvas.save();
          canvas.clipRect(imageX,imageY,imageX + imageW,imageY + imageH);
          if (orientation % 360 != 0) {
            if (centerRotation) {
              canvas.rotate(orientation,imageW / 2,imageH / 2);
            }
 else {
              canvas.rotate(orientation,0,0);
            }
          }
          if (bitmapW / scaleH > imageW) {
            bitmapW/=scaleH;
            drawRegion.set(imageX - (bitmapW - imageW) / 2.0f,imageY,imageX + (bitmapW + imageW) / 2.0f,imageY + imageH);
          }
 else {
            bitmapH/=scaleW;
            drawRegion.set(imageX,imageY - (bitmapH - imageH) / 2.0f,imageX + imageW,imageY + (bitmapH + imageH) / 2.0f);
          }
          if (bitmapDrawable instanceof AnimatedFileDrawable) {
            ((AnimatedFileDrawable)bitmapDrawable).setActualDrawRect(imageX,imageY,imageW,imageH);
          }
          if (orientation % 360 == 90 || orientation % 360 == 270) {
            float width=drawRegion.width() / 2;
            float height=drawRegion.height() / 2;
            float centerX=drawRegion.centerX();
            float centerY=drawRegion.centerY();
            bitmapDrawable.setBounds((int)(centerX - height),(int)(centerY - width),(int)(centerX + height),(int)(centerY + width));
          }
 else {
            bitmapDrawable.setBounds((int)drawRegion.left,(int)drawRegion.top,(int)drawRegion.right,(int)drawRegion.bottom);
          }
          if (isVisible) {
            try {
              bitmapDrawable.setAlpha(alpha);
              bitmapDrawable.draw(canvas);
            }
 catch (            Exception e) {
              onBitmapException(bitmapDrawable);
              FileLog.e(e);
            }
          }
          canvas.restore();
        }
 else {
          canvas.save();
          if (orientation % 360 != 0) {
            if (centerRotation) {
              canvas.rotate(orientation,imageW / 2,imageH / 2);
            }
 else {
              canvas.rotate(orientation,0,0);
            }
          }
          drawRegion.set(imageX,imageY,imageX + imageW,imageY + imageH);
          if (bitmapDrawable instanceof AnimatedFileDrawable) {
            ((AnimatedFileDrawable)bitmapDrawable).setActualDrawRect(imageX,imageY,imageW,imageH);
          }
          if (orientation % 360 == 90 || orientation % 360 == 270) {
            float width=drawRegion.width() / 2;
            float height=drawRegion.height() / 2;
            float centerX=drawRegion.centerX();
            float centerY=drawRegion.centerY();
            bitmapDrawable.setBounds((int)(centerX - height),(int)(centerY - width),(int)(centerX + height),(int)(centerY + width));
          }
 else {
            bitmapDrawable.setBounds((int)drawRegion.left,(int)drawRegion.top,(int)drawRegion.right,(int)drawRegion.bottom);
          }
          if (isVisible) {
            try {
              bitmapDrawable.setAlpha(alpha);
              bitmapDrawable.draw(canvas);
            }
 catch (            Exception e) {
              onBitmapException(bitmapDrawable);
              FileLog.e(e);
            }
          }
          canvas.restore();
        }
      }
    }
  }
 else {
    drawRegion.set(imageX,imageY,imageX + imageW,imageY + imageH);
    drawable.setBounds((int)drawRegion.left,(int)drawRegion.top,(int)drawRegion.right,(int)drawRegion.bottom);
    if (isVisible) {
      if (drawable instanceof LottieDrawable) {
        canvas.save();
        float tx=imageX;
        float ty=imageY;
        int bitmapWidth=getBitmapWidth();
        int bitmapHeight=getBitmapHeight();
        float scale;
        if (bitmapWidth > imageW || bitmapHeight > imageH) {
          scale=Math.min(imageW / (float)bitmapWidth,imageH / (float)bitmapHeight);
          bitmapWidth*=scale;
          bitmapHeight*=scale;
          canvas.scale(scale,scale);
        }
 else {
          scale=1.0f;
        }
        canvas.translate((imageX + (imageW - bitmapWidth) / 2) / scale,(imageY + (imageH - bitmapHeight) / 2) / scale);
        if (parentView != null) {
          if (invalidateAll) {
            parentView.invalidate();
          }
 else {
            parentView.invalidate(imageX,imageY,imageX + imageW,imageY + imageH);
          }
        }
      }
      try {
        drawable.setAlpha(alpha);
        drawable.draw(canvas);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      if (drawable instanceof LottieDrawable) {
        canvas.restore();
      }
    }
  }
}
