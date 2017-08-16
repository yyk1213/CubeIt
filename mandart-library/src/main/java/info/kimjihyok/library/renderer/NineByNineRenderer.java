package info.kimjihyok.library.renderer;


import android.graphics.Canvas;

import info.kimjihyok.library.ItemInterface;

public class NineByNineRenderer implements Renderer {
  @Override
  public void render(Canvas canvas, ItemInterface[][] items) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j< 9; j++) {
        canvas.drawRect(items[i][j].getRect(), items[i][j].getInnerPaint());
        canvas.drawRect(items[i][j].getRect(), items[i][j].getBorderPaint());
        canvas.drawText(items[i][j].getText(), items[i][j].getRect().centerX(), items[i][j].getRect().centerY(), items[i][j].getTextPaint());
      }
    }
  }
}
