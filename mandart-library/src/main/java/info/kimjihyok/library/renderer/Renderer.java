package info.kimjihyok.library.renderer;


import android.graphics.Canvas;

import info.kimjihyok.library.ItemInterface;

public interface Renderer {
  void render(Canvas canvas, ItemInterface[][] items);
}
