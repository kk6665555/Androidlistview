package com.example.mac.mylistview;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    List<Map<String,String>> data;
    String[] from = {"1","2"};
    int[] to ={R.id.title,R.id.content};
    SimpleAdapter adapter;
    private int removeIndex = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(ListView)findViewById(R.id.list);
        initList();
    }

    private void initList(){
        data = new LinkedList<>();
        Map<String,String> d0 = new HashMap<>();
        d0.put("1","Android");
        d0.put("2","Android....");
        data.add(d0);

        Map<String,String> d1 = new HashMap<>();
        d1.put("1","Android1");
        d1.put("2","Android1....");
        data.add(d1);




        adapter=new SimpleAdapter(this,data,R.layout.layout_item,from,to);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("test","click"+ i);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("test", "Long" + l);
                removeIndex = i;
                return true;
            }
        });
    }

    private void showDeletedialog(){
        AlertDialog dialog =null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warn");
        builder.setMessage("Delete"+data.get(removeIndex).get(1+"?"));
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                removeIndex=-1;

            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.setCancelable(false);
        dialog=builder.create();
        dialog.show();

    }

    private void clearItem(){

    }

    public void add(View view){
        Map<String,String> d1 = new HashMap<>();
        d1.put("1","Android1");
        d1.put("2","Android1...."+(int)(Math.random()*44+1));
        data.add(d1);
        adapter.notifyDataSetChanged();
    }
}


