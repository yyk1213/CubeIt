package info.kimjihyok.library.fragment;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.kimjihyok.library.R;

public class DreamListFragment extends Fragment {
  public static final String TAG = "DreamListFragment";
  private static final String MODE_KEY = "mode_key";
  private static final String TARGET_KEY = "target_key";
  private static final String LIST_KEY = "list_key";

  public enum Mode {
    EDIT_MODE, VIEW_MODE
  }

  public DreamListFragment() {
    // Required empty public constructor
  }

  private Mode currentMode;
  private List<String> dreamList;
  private String targetDream;

  private ListView listView;
  private TextView dreamTextView;

  public static DreamListFragment newInstance(Mode mode, String target, ArrayList<String> dreamList) {
    DreamListFragment fragment = new DreamListFragment();
    Bundle args = new Bundle();
    args.putSerializable(MODE_KEY, mode);
    args.putString(TARGET_KEY, target);
    args.putStringArrayList(LIST_KEY, dreamList);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      currentMode = (Mode) getArguments().getSerializable(MODE_KEY);
      dreamList = getArguments().getStringArrayList(LIST_KEY);
      targetDream = getArguments().getString(TARGET_KEY);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_dream_list, container, false);

    dreamTextView = (TextView) view.findViewById(R.id.dream_text_view);
    dreamTextView.setText(targetDream);
    listView = (ListView) view.findViewById(R.id.dream_list_view);
    listView.setAdapter(new DreamListAdapter(dreamList));

    return view;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    getActivity().getMenuInflater().inflate(R.menu.search, menu);
    SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
    SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
  }

  public class DreamListAdapter<String> extends BaseAdapter {
    private List<String> dreamItems;

    public DreamListAdapter(List<String> dreamItems) {
      this.dreamItems = dreamItems;
    }

    @Override
    public int getCount() {
      return dreamItems.size();
    }

    @Override
    public String getItem(int position) {
      return dreamItems.get(position);
    }

    @Override
    public long getItemId(int position) {
      return dreamItems.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      final Context context = parent.getContext();
      if (convertView == null) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.listview_item, parent, false);
      }

      TextView titleTextView = (TextView) convertView.findViewById(R.id.list_row_item_textview);
      titleTextView.setText("계획 " + (position + 1) + ". " + dreamItems.get(position));

      return convertView;
    }
  }
}
