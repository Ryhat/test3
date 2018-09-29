package com;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.DataBase.DataBaseHelper;
import com.tasker.chrisou.test3.MainActivity;
import com.tasker.chrisou.test3.R;

import java.util.ArrayList;

import static com.tasker.chrisou.test3.MainActivity.fragmentThree;

public class FragmentTwo extends ListFragment implements AdapterView.OnItemClickListener, FragmentThree.OnInputListener, AdapterView.OnItemLongClickListener {
    private static final String TAG = "FragmentTwo";
    public ArrayAdapter<String> arrayAdapter;
    public ArrayList<String> arrayList;

    DataBaseHelper myDb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two, container, false);

        fragmentThree.setTargetFragment(FragmentTwo.this, 1);
        Log.d(TAG, "onCreateView: created view.");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //数据库
        myDb = new DataBaseHelper(getActivity());
        arrayList = new ArrayList<String>();
        Cursor res = myDb.getAllData();
        if(res != null){
            while (res.moveToNext()) {
                arrayList.add(res.getString(1));
                Log.d(TAG, "get:" + res.getString(1));
            }
            Toast.makeText(getActivity(), "Data retrieved successfully!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "Data retrieved failed!", Toast.LENGTH_SHORT).show();
        }
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, arrayList);
        setListAdapter(arrayAdapter);

        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);
        registerForContextMenu(getListView());
        Log.d(TAG, "onActivityCreated: activity created.");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //String[] listItem = (String[])
        Toast.makeText(getActivity(), arrayList.get(i), Toast.LENGTH_SHORT).show();

        //PopupMenu popupMenu = new PopupMenu(getActivity());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d(TAG, "onContextItemSelected: " + item.getTitle());

        if(item.getTitle().toString().equals("delete")){
            Toast.makeText(getActivity(), "delete", Toast.LENGTH_SHORT).show();
            return true;
        }else if(item.getTitle().toString().equals("edit")){
            Toast.makeText(getActivity(), "edit", Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            return super.onContextItemSelected(item);
        }

    }

    @Override
    public void sendInput(String input) {
        Log.d(TAG, "sendInput: got the input: "+ input);
        arrayList.add(input);
        arrayAdapter.notifyDataSetChanged();
        Boolean result = myDb.insertData(input,null,null,null);
        if(result){
            Toast.makeText(getActivity(), "Data inserted successfully!", Toast.LENGTH_SHORT).show();
            //textView = context.findViewById(R.id.textToShow);
        }else{
            Toast.makeText(getActivity(), "Data inserted failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
       // PopupMenu popupMenu = new PopupMenu(getActivity(), )
        Toast.makeText(getActivity(), "!!!!!", Toast.LENGTH_SHORT).show();
        return true;
    }
}
