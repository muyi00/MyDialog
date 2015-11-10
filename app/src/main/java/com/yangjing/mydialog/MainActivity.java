package com.yangjing.mydialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.dialog1).setOnClickListener(this);
        findViewById(R.id.dialog2).setOnClickListener(this);
        findViewById(R.id.dialog3).setOnClickListener(this);
    }


    private void ShowToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog1:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("AlertDialog");
                builder.setNegativeButton("按钮1", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ShowToast("按钮1");
                    }
                });

                builder.setNeutralButton("按钮2", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ShowToast("按钮2");
                    }
                });

                builder.setPositiveButton("按钮3", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ShowToast("按钮3");
                        dialog.cancel();
                    }
                });

                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        ShowToast("OnCancelListener");
                    }
                });
                builder.setCancelable(false);
                builder.create();
                builder.show();

                break;
            case R.id.dialog2:
                startActivityForResult(new Intent(this, DialogActivity.class), 1, null);
                break;
            case R.id.dialog3:
                View popupwindow_view = getLayoutInflater().inflate(R.layout.activity_dialog, null, false);
                final PopupWindow popupWindow = new PopupWindow(popupwindow_view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
                PopupWindowClickListener listener = new PopupWindowClickListener(popupWindow);
                TextView textView= (TextView) popupwindow_view.findViewById(R.id.textview_id);
                textView.setText("PopupWindow");
                popupwindow_view.findViewById(R.id.btn1).setOnClickListener(listener);
                popupwindow_view.findViewById(R.id.btn2).setOnClickListener(listener);
                popupwindow_view.findViewById(R.id.btn3).setOnClickListener(listener);
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                popupwindow_view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
//                        if (popupWindow != null && popupWindow.isShowing()) {
//                            popupWindow.dismiss();
//
//                        }
                        return false;
                    }
                });
                break;
        }
    }

    private class PopupWindowClickListener implements View.OnClickListener {
        private PopupWindow pop;

        public PopupWindowClickListener(PopupWindow pop) {
            this.pop = pop;
        }

        @Override
        public void onClick(View v) {
            pop.dismiss();
            switch (v.getId()) {
                case R.id.btn1:
                    ShowToast("PopupWindow中点击按钮1");
                    break;
                case R.id.btn2:
                    ShowToast("PopupWindow中点击按钮2");
                    break;
                case R.id.btn3:
                    ShowToast("PopupWindow中点击按钮3");
                    break;
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            if (data == null) {
                data.putExtra("btn", 0);
            }
            switch (data.getIntExtra("btn", 0)) {
                case 0:
                    ShowToast("DialogActivity没有点击任何按钮");
                    break;
                case 1:
                    ShowToast("DialogActivity点击按钮1");
                    break;
                case 2:
                    ShowToast("DialogActivity点击按钮2");
                    break;
                case 3:
                    ShowToast("DialogActivity点击按钮3");
                    break;
            }
        }
    }
}
