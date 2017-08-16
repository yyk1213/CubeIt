package info.kimjihyok.library.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import info.kimjihyok.library.R;
import info.kimjihyok.library.widget.ZoomableLayout;

public class ZoomableGridFragment extends Fragment {
  public static final String TAG = "ZoomableGridFragment";

  private List<Button> targetButtons;
  private Button targetDream;
  private ZoomableLayout zoomableLayout;
  private ZoomableLayout.MaxZoomListener maxZoomListener;


  private List<String> userTargetItems;
  private String userDream;

  public void setMaxZoomListener(ZoomableLayout.MaxZoomListener maxZoomListener) {
    this.maxZoomListener = maxZoomListener;
  }


  public void resetView() {
    zoomableLayout.reset();
  }

  public void setDream(String userDream) {
    this.userDream = userDream;
  }

  public void setTargetItems(List<String> userTargetItems) {
    this.userTargetItems = userTargetItems;
  }

  public static ZoomableGridFragment newInstance() {
    ZoomableGridFragment fragment = new ZoomableGridFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {

    }

    targetButtons = new ArrayList<>();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_zoomable_grid, container, false);

    zoomableLayout = (ZoomableLayout) view.findViewById(R.id.zoomable_layout);
    zoomableLayout.setMaxZoomListener(maxZoomListener);

    targetButtons.add((Button) view.findViewById(R.id.mandart_item_1));
    targetButtons.add((Button) view.findViewById(R.id.mandart_item_2));
    targetButtons.add((Button) view.findViewById(R.id.mandart_item_3));
    targetButtons.add((Button) view.findViewById(R.id.mandart_item_4));
    targetButtons.add((Button) view.findViewById(R.id.mandart_item_5));
    targetButtons.add((Button) view.findViewById(R.id.mandart_item_6));
    targetButtons.add((Button) view.findViewById(R.id.mandart_item_7));
    targetButtons.add((Button) view.findViewById(R.id.mandart_item_8));

    targetDream = (Button) view.findViewById(R.id.mandart_item_dream);
    targetDream.setText(userDream);

    for (int i = 0; i < targetButtons.size(); i++) {
      targetButtons.get(i).setText(userTargetItems.get(i));
    }

    return view;
  }

}
