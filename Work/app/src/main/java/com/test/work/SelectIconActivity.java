package com.test.work;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectIconActivity extends Activity {
    GridView gridView;
    int [] imageIds=new int[]{
            R.drawable.img01, R.drawable.img02, R.drawable.img03,
            R.drawable.img04, R.drawable.img05, R.drawable.img06,
            R.drawable.img07, R.drawable.img08, R.drawable.img09
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selecticon);
        gridView=(GridView)findViewById(R.id.root);
        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < imageIds.length; i++) {
            Map<String,Object> listItem=new HashMap<String,Object>();
            listItem.put("image",imageIds[i]);
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listItems,R.layout.cell,
                new String[]{"image"},new int[]{R.id.image}
        );
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putInt("imageId",imageIds[i]);
                intent.putExtras(bundle);
                setResult(1,intent);
                finish();
            }
        });
    }
}
