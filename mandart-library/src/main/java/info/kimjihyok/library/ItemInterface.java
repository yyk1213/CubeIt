package info.kimjihyok.library;


import android.graphics.Paint;
import android.graphics.Rect;

public interface ItemInterface {
  Rect getRect();
  void setRect(Rect rect);

  Paint getInnerPaint();
  void setInnerPaint(Paint paint);

  Paint getBorderPaint();
  void setBorderPaint(Paint paint);

  //TODO: to be changed to set model
  void setText(String s, Paint paint);
  String getText();
  Paint getTextPaint();
}
