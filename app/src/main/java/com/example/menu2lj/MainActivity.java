package com.example.menu2lj;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_ui_elements_actions,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // return false if nothing is done
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case 1000008:
                    Log.d("info", "edit chosen");
                    mode.finish(); // accion seleccionada, cerramos el CAB
                    return true;
                case 1000007:
                    Log.d("info", "delete chosen");
                    mode.finish(); // accion selecionad, cerramos el CAB
                    return true;
                default:
                    return false;
            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;

        }
    };
  

    ActionMode actionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView tvItem1;
        tvItem1 = findViewById(R.id.item1);
        final TextView tvItem2 = findViewById(R.id.item2);
        TextView tvItem3 = findViewById(R.id.item3);
        registerForContextMenu(tvItem1);
        tvItem2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (actionMode != null){
                    return false;
                }
                actionMode = (MainActivity.this).startActionMode(actionModeCallback);
                tvItem2.setSelected(true);
                return true;
            }
        });
        tvItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this,v);
                // esta implementada en el menu itemclicklistener
                popup.setOnMenuItemClickListener(MainActivity.this);
                popup.inflate(R.menu.menu_popup);
                popup.show();

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ui_elements_actions,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //case R.id.item_Edit:
            // 1000008
            case 1000008:
                Log.d("info","edit chosen");
                return true;
            //case R.id.item_Delete:

            //1000007
            case 1000007:
                Log.d("info","delete chosen");
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }



    //esos dos metodos son del menu de opciones, el de 3 puntos
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app_actions,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //mantener item selecionado
        switch (item.getItemId()){
            //case R.id.item_Settings:

            //1000001
            // porque el case no deja poner la sentencia,dice requerir una constante
            // cuando esto ya es una constante
            case 1000001:
                Log.d("OpciontsMenu","Settings picked");
                return true;
                //case R.id.item_Logout:

            //1000002
            case 1000002:
                Log.d("OptionsMenu","Logout clicked");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            //case R.id.item_Share:

            //1000005
            case 1000005:
                Log.d("popup","share chosen");
                return true;
            default:
                return false;
        }

    }
}